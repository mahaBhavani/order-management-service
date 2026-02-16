package com.example.ordermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false)
    private String name;
    
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    @Column(nullable = false, unique = true)
    private String email;
}
