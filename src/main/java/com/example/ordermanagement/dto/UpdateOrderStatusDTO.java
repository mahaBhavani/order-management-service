package com.example.ordermanagement.dto;

import com.example.ordermanagement.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusDTO {

    @NotNull(message = "Status must not be null")
    private OrderStatus status;
}

