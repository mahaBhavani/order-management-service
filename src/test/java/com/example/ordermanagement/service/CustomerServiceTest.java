package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.CustomerRequestDTO;
import com.example.ordermanagement.dto.CustomerResponseDTO;
import com.example.ordermanagement.entity.Customer;
import com.example.ordermanagement.exception.ResourceNotFoundException;
import com.example.ordermanagement.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer testCustomer;
    private CustomerRequestDTO customerRequestDTO;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("John Doe");
        testCustomer.setEmail("john@example.com");

        customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");
        customerRequestDTO.setEmail("john@example.com");
    }

    @Test
    void createCustomer_ShouldReturnCustomerResponseDTO_WhenValidRequest() {
        // Given
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // When
        CustomerResponseDTO result = customerService.createCustomer(customerRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getCustomerById_ShouldReturnCustomerResponseDTO_WhenCustomerExists() {
        // Given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        // When
        CustomerResponseDTO result = customerService.getCustomerById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getCustomerById_ShouldThrowResourceNotFoundException_WhenCustomerDoesNotExist() {
        // Given
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> customerService.getCustomerById(999L)
        );

        assertEquals("Customer not found with id: 999", exception.getMessage());
        verify(customerRepository, times(1)).findById(999L);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomerResponseDTOs_WhenCustomersExist() {
        // Given
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");

        List<Customer> customers = Arrays.asList(testCustomer, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        // When
        List<CustomerResponseDTO> result = customerService.getAllCustomers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void getAllCustomers_ShouldReturnEmptyList_WhenNoCustomersExist() {
        // Given
        when(customerRepository.findAll()).thenReturn(List.of());

        // When
        List<CustomerResponseDTO> result = customerService.getAllCustomers();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void createCustomer_ShouldVerifyRepositorySaveCalledWithCorrectData() {
        // Given
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // When
        customerService.createCustomer(customerRequestDTO);

        // Then
        verify(customerRepository, times(1)).save(argThat(customer -> 
            customer.getName().equals("John Doe") && 
            customer.getEmail().equals("john@example.com")
        ));
    }
}
