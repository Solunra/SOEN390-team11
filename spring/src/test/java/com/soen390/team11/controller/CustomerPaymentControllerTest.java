package com.soen390.team11.controller;

import com.soen390.team11.dto.CustomerPaymentDto;
import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.entity.Payment;
import com.soen390.team11.service.CustomerPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerPaymentControllerTest {

    @InjectMocks
    CustomerPaymentController customerPaymentController;

    @Mock
    CustomerPaymentService customerPaymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Payment payment= new Payment();
        when(customerPaymentService.createPayment(any(CustomerPaymentDto.class))).thenReturn(payment);

        CustomerPaymentDto customerPaymentDto = new CustomerPaymentDto();
        ResponseEntity<?> responseEntity = customerPaymentController.createPayment(customerPaymentDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);

    }

    @Test
    void updatePayment()  {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String updatePaymentId = "updated-payment-uuid";
        when(customerPaymentService.updatePayment(any(CustomerPaymentDto.class),anyString())).thenReturn(updatePaymentId);

        CustomerPaymentDto customerPaymentDto = new CustomerPaymentDto();
        ResponseEntity<?> responseEntity = customerPaymentController.updatePayment(updatePaymentId,customerPaymentDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void deletePayment() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String deletedCustomerId = "deleted-customer-uuid";
        when(customerPaymentService.deletePaymentById(anyString())).thenReturn("success");
        ResponseEntity<?> responseEntity = customerPaymentController.deletePayment(deletedCustomerId);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}