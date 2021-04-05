package com.soen390.team11.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.CustomizeReportDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.service.CustomerPurchaseService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerPurchaseControllerTest {

    @InjectMocks
    CustomerPurchaseController customerPurchaseController;

    @Mock
    CustomerPurchaseService customerPurchaseService;

    CustomerPurchaseDto customerPurchaseDto;

    String invoiceid = "id";

    @BeforeEach
    public void setup()
    {
        openMocks(this);

        List<HashMap<String, Object>> cart = new ArrayList<>();

        HashMap<String, Object> item= new HashMap<>();
        item.put("productid","Aero-lbm");
        item.put("quantity",1);
        cart.add(item);
        customerPurchaseDto = new CustomerPurchaseDto("firstname","lastname","address","city","province"
            ,"zip","country",2000.0,cart);
    }

    @Test
    void makePurchase() {
        when(customerPurchaseService.makePurchase(customerPurchaseDto)).thenReturn(invoiceid);
        ResponseEntity<?> responseEntity = customerPurchaseController.makePurchase(customerPurchaseDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void getStatus() {
        when(customerPurchaseService.checkStatus(invoiceid)).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getStatus(invoiceid);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllProduct() {
        when(customerPurchaseService.getAllProduct()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getAllProduct();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getCustomizeProduct() {
        ProductRequestDto productRequestDto= new ProductRequestDto();
        productRequestDto.setName("Aero bikes");
        productRequestDto.setColor("Blue");
        productRequestDto.setSize("Large");
        productRequestDto.setFinish("Matte");

        when(customerPurchaseService.getCustomerizeProduct(productRequestDto)).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getCustomizeProduct(productRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void getAllOrder() {
        when(customerPurchaseService.getAllProduct()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getAllOrder();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void orderActions() {
        when(customerPurchaseService.orderActions("Aero-lbm", invoiceid)).thenReturn("");
        ResponseEntity<?> responseEntity = customerPurchaseController.orderActions("Aero-lbm",invoiceid);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccountOrder() {
        when(customerPurchaseService.getAllAccountOrder()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getAllAccountOrder();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getReport() {
        CustomizeReportDto customizeReportDto = new CustomizeReportDto(LocalDate.of(2021,3, 27),LocalDate.of(2021,3, 28));
        when(customerPurchaseService.getCustomizeReport(customizeReportDto)).thenReturn(List.of());
        ResponseEntity<?> responseEntity = customerPurchaseController.getReport(customizeReportDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}