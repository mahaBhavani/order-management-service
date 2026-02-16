package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.OrderRequestDTO;
import com.example.ordermanagement.dto.OrderResponseDTO;
import com.example.ordermanagement.entity.Customer;
import com.example.ordermanagement.entity.Order;
import com.example.ordermanagement.entity.OrderStatus;
import com.example.ordermanagement.exception.ResourceNotFoundException;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    private Customer testCustomer;
    private Order testOrder;
    private OrderRequestDTO orderRequestDTO;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("John Doe");
        testCustomer.setEmail("john@example.com");

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setProductName("Laptop");
        testOrder.setQuantity(2);
        testOrder.setPrice(new BigDecimal("50000"));
        testOrder.setStatus(OrderStatus.CREATED);
        testOrder.setCustomer(testCustomer);

        orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setProductName("Laptop");
        orderRequestDTO.setQuantity(2);
        orderRequestDTO.setPrice(new BigDecimal("50000"));
        orderRequestDTO.setCustomerId(1L);
    }

    @Test
    void createOrder_ShouldReturnOrderResponseDTO_WhenCustomerExists() {
        // Given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        // When
        OrderResponseDTO result = orderService.createOrder(orderRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(new BigDecimal("50000"), result.getPrice());
        assertEquals(OrderStatus.CREATED, result.getStatus());
        assertEquals(1L, result.getCustomerId());
        verify(customerRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void createOrder_ShouldThrowResourceNotFoundException_WhenCustomerNotFound() {
        // Given
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());
        orderRequestDTO.setCustomerId(999L);

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.createOrder(orderRequestDTO)
        );

        assertEquals("Customer not found with id: 999", exception.getMessage());
        verify(customerRepository, times(1)).findById(999L);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void getOrderById_ShouldReturnOrderResponseDTO_WhenOrderExists() {
        // Given
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        // When
        OrderResponseDTO result = orderService.getOrderById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getProductName());
        assertEquals(2, result.getQuantity());
        assertEquals(new BigDecimal("50000"), result.getPrice());
        assertEquals(OrderStatus.CREATED, result.getStatus());
        assertEquals(1L, result.getCustomerId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void getOrderById_ShouldThrowResourceNotFoundException_WhenOrderNotFound() {
        // Given
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.getOrderById(999L)
        );

        assertEquals("Order not found with id: 999", exception.getMessage());
        verify(orderRepository, times(1)).findById(999L);
    }

    @Test
    void updateOrderStatus_ShouldReturnOrderResponseDTO_WhenOrderExists() {
        // Given
        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        updatedOrder.setProductName("Laptop");
        updatedOrder.setQuantity(2);
        updatedOrder.setPrice(new BigDecimal("50000"));
        updatedOrder.setStatus(OrderStatus.COMPLETED);
        updatedOrder.setCustomer(testCustomer);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);

        // When
        OrderResponseDTO result = orderService.updateOrderStatus(1L, OrderStatus.COMPLETED);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(OrderStatus.COMPLETED, result.getStatus());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void deleteOrder_ShouldDeleteOrder_WhenOrderExists() {
        // Given
        when(orderRepository.existsById(1L)).thenReturn(true);

        // When
        orderService.deleteOrder(1L);

        // Then
        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteOrder_ShouldThrowResourceNotFoundException_WhenOrderNotFound() {
        // Given
        when(orderRepository.existsById(999L)).thenReturn(false);

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.deleteOrder(999L)
        );

        assertEquals("Order not found with id: 999", exception.getMessage());
        verify(orderRepository, times(1)).existsById(999L);
        verify(orderRepository, never()).deleteById(any());
    }
}
