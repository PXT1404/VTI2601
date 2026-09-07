//package com.example.authservice.Config;
//
//import com.example.authservice.common.BaseResponse;
//import org.jboss.resteasy.spi.ApplicationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        var listErrors = ex.getBindingResult().getFieldErrors();
//        List<String> message = new ArrayList<>();
//        for (var error : listErrors){
//            message.add(error.getField() + ": " + error.getDefaultMessage() );
//        };
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(null, String.join("; ",message)));
//    }
//
//    @ExceptionHandler(ApplicationException.class)
//    public ResponseEntity<?> handleAppException(ApplicationException ex) {
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(ex.getMessage());
//    }
//
//}