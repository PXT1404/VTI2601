package com.example.authservice.service.impl;

import com.example.authservice.entity.User;
import com.example.authservice.mapper.UserMapper;
import com.example.authservice.rabbitmqClient.server.RabbitMQSender;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.request.LoginRequestDTO;
import com.example.authservice.request.UserRegistrationDTO;
import com.example.authservice.response.LoginResponseDTO;
import com.example.authservice.service.UserService;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Keycloak keycloak;
    private final UserMapper userMapper;

    @Autowired
    private final RabbitMQSender rabbitMQSender;

    @Value("${keycloak.auth-service-url}")
    private String serverUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;


    @Override
    public User createUser(UserRegistrationDTO dto) {
        // 1. Khởi tạo thông tin User cho Keycloak
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());

        // 2. Thiết lập Password cho Keycloak
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.getPassword());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = keycloak.realm(realm).users();
        Response response = usersResource.create(user);

        // 1. Kiểm tra Role hợp lệ trước khi gọi sang Keycloak
        String inputRole = dto.getRole();
        if (inputRole == null || (!inputRole.equalsIgnoreCase("ADMIN")  && !inputRole.equalsIgnoreCase("USER"))) {
            log.error("Invalid role: {}. Only ADMIN or USERS are allowed.", inputRole);
            throw new IllegalArgumentException("Role không hợp lệ. Chỉ chấp nhận ADMIN hoặc USERS.");
        }

// 2. Nếu Role hợp lệ, tiến hành gọi Keycloak (đoạn code tạo user của bạn ở trên)
// Response response = keycloak.users().create(userRepresentation);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            // GÁN ROLE CHỦ ĐỘNG: ADMIN hoặc USER đều gán qua API
            try {
                // Dù là ADMIN hay USER, chúng ta đều gọi sang Keycloak để gán
                // Điều này giúp Token chắc chắn có Role
                assignClientRole(userId, inputRole.toUpperCase());
                log.info("Role '{}' assigned to user: {}", inputRole, dto.getUsername());
            } catch (Exception e) {
                log.error("Failed to assign role: {}", e.getMessage());
                // Có thể cân nhắc throw exception ở đây để rollback nếu cần
            }
            rabbitMQSender.sendAccountCreatedEvent(dto);

            // 4. Lưu vào Database Local
            User newUser = userMapper.createUser(dto);
            newUser.setIsDeleted(false);
            newUser.setCreatedDate(Instant.now());

            User savedUser = userRepository.save(newUser);
            log.info("Successfully saved user to local database: {}", savedUser.getUsername());

            return savedUser;
        } else if (response.getStatus() == 409) {
            log.error("User already exists in Keycloak: {}", dto.getUsername());
            throw new RuntimeException("Username or Email already exists");
        } else {
            log.error("Error creating user, status: {}", response.getStatus());
            throw new RuntimeException("Failed to create user in Keycloak");
        }
    }

    private void assignClientRole(String userId, String roleName) {
        // Bước 1: Tìm UUID của client 'backend'
        String clientUuid = keycloak.realm(realm).clients()
                .findByClientId(clientId).get(0).getId();

        // Bước 2: Lấy thông tin role 'ADMIN' từ client đó
        var roleRepresentation = keycloak.realm(realm).clients()
                .get(clientUuid).roles().get(roleName).toRepresentation();

        // Bước 3: Gán role vào cấp độ Client cho User
        keycloak.realm(realm).users().get(userId).roles()
                .clientLevel(clientUuid).add(Collections.singletonList(roleRepresentation));
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        try (Keycloak keycloakClient = Keycloak.getInstance(
                serverUrl, realm, dto.getUsername(), dto.getPassword(),  clientId, clientSecret)) {
            AccessTokenResponse accessTokenResponse = keycloakClient.tokenManager().getAccessToken();
            return LoginResponseDTO.builder()
                    .accessToken(accessTokenResponse.getToken())
                    .refreshToken(accessTokenResponse.getRefreshToken())
                    .build();
        } catch (NotAuthorizedException e) {
            throw new RuntimeException("Invalid credentials",e);
        }
    }
}
