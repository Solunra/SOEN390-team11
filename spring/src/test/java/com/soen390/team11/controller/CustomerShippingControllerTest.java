package com.soen390.team11.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.service.CustomerShippingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


class CustomerShippingControllerTest {

    @InjectMocks
    CustomerShippingController customerShippingController;

    @Mock
    CustomerShippingService customerShippingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCompleteCustomer_Success()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String newCustomerId = "new-customer-uuid";
        when(customerShippingService.createCustomer(any(CustomerShippingDto.class))).thenReturn(newCustomerId);

        CustomerShippingDto customer = new CustomerShippingDto();
        ResponseEntity<?> responseEntity = customerShippingController.createCustomer(customer);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(newCustomerId);
    }


    @Test
    public void updateExistingCustomer_Success()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String updatedCustomerId = "updated-customer-uuid";
        when(customerShippingService.updateCustomer(any(CustomerShippingDto.class))).thenReturn(updatedCustomerId);

        CustomerShippingDto customer = new CustomerShippingDto();
        ResponseEntity<?> responseEntity = customerShippingController.updateCustomer(customer);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(updatedCustomerId);
    }
}