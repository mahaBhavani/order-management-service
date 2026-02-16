package com.example.ordermanagement.dto;

import com.example.ordermanagement.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private OrderStatus status;
    private Long customerId;
}
