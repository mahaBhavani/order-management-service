package com.example.ordermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    
    @NotBlank(message = "Product name must not be blank")
    private String productName;
    
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;
    
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;
    
    @NotNull(message = "Customer ID must not be null")
    private Long customerId;
}
