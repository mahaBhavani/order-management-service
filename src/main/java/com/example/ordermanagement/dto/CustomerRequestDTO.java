package com.example.ordermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    
    @NotBlank(message = "Name must not be blank")
    private String name;
    
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;
}
