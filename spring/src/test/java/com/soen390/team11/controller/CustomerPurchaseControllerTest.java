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
import org.junit.jupiter.api.*;
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
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerPurchaseControllerTest {
    @Autowired
    CustomerPurchaseController customerPurchaseController;
    @Autowired
    CustomerPurchaseRepository customerPurchaseRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    CustomerPurchaseDto customerPurchaseDto;
    String invoiceid="";
    @BeforeAll
    public void setup()
    {
        List<HashMap<String, Object>> carte = new ArrayList<>();
        HashMap<String, Object> item= new HashMap<>();
        item.put("productid","Aero-lbm");
        item.put("quantity",1);
        carte.add(item);
        customerPurchaseDto = new CustomerPurchaseDto("firstname","lastname","address","city","province"
            ,"zip","country",2000.0,carte);
    }
    @Test
    @Order(1)
    void makePurchase() {
        ResponseEntity<?> responseEntity = customerPurchaseController.makePurchase(customerPurchaseDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        invoiceid = ((String) responseEntity.getBody()).replace("\"","");
    }

    @Test
    @Order(2)
    void getStatus() {
        ResponseEntity<?> responseEntity = customerPurchaseController.getStatus(invoiceid);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    void getAllProduct() {
        ResponseEntity<?> responseEntity = customerPurchaseController.getAllProduct();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(4)
    void getCustomizeProduct() {
        ProductRequestDto productRequestDto= new ProductRequestDto();
        productRequestDto.setName("Aero bikes");
        productRequestDto.setColor("Blue");
        productRequestDto.setSize("Large");
        productRequestDto.setFinish("Matte");
        ResponseEntity<?> responseEntity = customerPurchaseController.getCustomizeProduct(productRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @AfterAll
    public void delete()
    {
        CustomerPurchase customerPurchase= customerPurchaseRepository.findByCustomerPurchaseIdInvoiceID(invoiceid).get();
        invoiceRepository.deleteByInvoiceID(invoiceid);
        customerRepository.deleteByCustomerID(customerPurchase.getCustomerPurchaseId().getCustomerID());
    }
}