package com.soen390.team11.controller;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.Invoice;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.CustomerRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.service.CustomerPurchaseService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CustomerPurchaseControllerTest {

    CustomerPurchaseController customerPurchaseController;
    @Mock
    CustomerPurchaseService customerPurchaseService;
    CustomerPurchaseDto customerPurchaseDto;
    String invoiceid = "id";

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        customerPurchaseController = new CustomerPurchaseController(customerPurchaseService);
        List<HashMap<String, Object>> carte = new ArrayList<>();
        HashMap<String, Object> item= new HashMap<>();
        item.put("productid","Aero-lbm");
        item.put("quantity",1);
        carte.add(item);
        customerPurchaseDto = new CustomerPurchaseDto("firstname","lastname","address","city","province"
            ,"zip","country",2000.0,carte);
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

}