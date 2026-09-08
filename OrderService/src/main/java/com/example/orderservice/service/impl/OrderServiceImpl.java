package com.example.orderservice.service.impl;

import com.example.orderservice.common.BaseResponse;
import com.example.orderservice.dto.DiscountType;
import com.example.orderservice.dto.request.OrderItemDTO;
import com.example.orderservice.dto.request.ProductDTO;
import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.dto.response.ProductResponse;
import com.example.orderservice.dto.response.PromotionResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.feignClient.ProductFeignClient;
import com.example.orderservice.feignClient.PromotionFeignClient;
import com.example.orderservice.form.FormForOrdering;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductFeignClient  productFeignClient;

    private final PromotionFeignClient promotionFeignClient;

    private final OrderItemRepository  orderItemRepository;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(FormForOrdering form) {
        Order order = new Order();
        order.setUserId("a");
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(0.0);
        orderRepository.save(order);

        List<OrderItemDTO> orderItems = form.getOrderItems();

        Double total = 0.0;

        for (OrderItemDTO orderItem : orderItems) {
            Double finalPrice = 0.0;
            Double discount = 0.0;
            String productId = orderItem.getProductId();
            Integer buyQuantity = orderItem.getQuantity();
            String codePromotion = orderItem.getCodePromotion();

            // Giao tiếp đồng bộ và xử lý dữ liệu PROMOTION gửi về
            BaseResponse<ProductResponse> responseProduct = productFeignClient.getProductById(productId);

            if (responseProduct == null || responseProduct.getData() == null) {
                throw new RuntimeException("Không có sản phẩm nào có id " + productId);
            }

            ProductResponse productData = responseProduct.getData();
            Integer stock = productData.getStock();

            if (stock == null || stock < buyQuantity) {
                throw new RuntimeException("Không còn đủ sản phẩm để mua cho ID: " + productId);
            }

            Double price = productData.getPrice();
            Double itemTotal = price * buyQuantity;

            // Giao tiếp đồng bộ và xử lý dữ liệu PROMOTION gửi về
            BaseResponse<PromotionResponse> promotionResponse = promotionFeignClient.getPromotionByCode(codePromotion);
            if (promotionResponse == null || promotionResponse.getData() == null) {
                throw new RuntimeException("Không có mã giảm giá là: " + codePromotion);
            }

            PromotionResponse promotionData = promotionResponse.getData();

            Instant now = Instant.now();
            if (promotionData.getStartDate().isAfter(now) || promotionData.getEndDate().isBefore(now)) {
                throw new RuntimeException(String.format("Code %s chưa tới hạn hoặc đã hết hạn sử dụng", codePromotion));
            }

// Kiểm tra điều kiện sử dụng mã
            if (promotionData.getUsageLimit() < 1 || itemTotal < promotionData.getMinOrderValue()) {
                discount = 0.0;
                log.info("Mã giảm giá không đủ điều kiện áp dụng (Hết lượt dùng hoặc chưa đạt giá trị đơn hàng tối thiểu)");
            } else {
                // Đảo ngược lại logic tính toán cho đúng bản chất
                if (promotionData.getDiscountType() == DiscountType.PERCENTAGE) {
                    // Giảm theo phần trăm (%)
                    discount = itemTotal * promotionData.getDiscountValue() / 100.0;
                } else if (promotionData.getDiscountType() == DiscountType.FIXED_AMOUNT) {
                    // Giảm số tiền cố định (VNĐ)
                    discount = Double.valueOf(promotionData.getDiscountValue());
                    // Đảm bảo tiền giảm không lớn hơn tổng tiền đơn hàng
                    if (discount > itemTotal) {
                        discount = itemTotal;
                    }
                }
            }
            finalPrice += itemTotal-discount;
            total += finalPrice;

            OrderItem orderItemNew = new OrderItem();
            orderItemNew.setOrderId(order.getId());
            orderItemNew.setProductId(productId);
            orderItemNew.setPromotionId(codePromotion);
            orderItemNew.setQuantity(buyQuantity);
            orderItemNew.setPrice(itemTotal);
            orderItemNew.setDiscount(discount);
            orderItemNew.setFinalPrice(finalPrice);

            orderItemRepository.save(orderItemNew);
        }

        order.setTotal(total);
        orderRepository.save(order);
        OrderResponse response = orderMapper.create(order);
        return response;
    }
}
