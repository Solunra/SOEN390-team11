package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.PartRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductPartsRepository;
import com.soen390.team11.repository.ProductRepository;
import com.soen390.team11.service.PartService;
import com.soen390.team11.service.ProductInventoryService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductInventoryControllerTest {

    ProductInventoryController productInventoryController;
    @Mock
    ProductInventoryService productInventoryService;
    @Mock
    PartService partService;

    ProductInventoryRequestDto productInventoryRequestDto;
    List<ProductInventory> productInventoryList = new ArrayList<>();
    List<Part> partList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<ProductParts> productPartsList = new ArrayList<>();
    ObjectMapper objectMapper= new ObjectMapper();
    ProductInventory productInventory;

    @BeforeEach
    public  void setup(){
        openMocks(this);
        Product product1 = new Product("bike1","mountain","medium","black","matte","A",0,0);
        Product product2 = new Product("bike2","road","large","red","matte","A",0,0);
        productList.add(product1);
        productList.add(product2);
        Part part1 = new Part("part1","part1");
        Part part2 = new Part("part2","part2");
        partList.add(part1);
        partList.add(part2);
        ProductPartsId productPartsId1 = new ProductPartsId(partList.get(0).getPartid(),productList.get(0).getProductid());
        ProductPartsId productPartsId2 = new ProductPartsId(partList.get(1).getPartid(),productList.get(0).getProductid());
        ProductParts productParts1 = new ProductParts(productPartsId1);
        ProductParts productParts2 = new ProductParts(productPartsId2);
        productPartsList.add(productParts1);
        productPartsList.add(productParts2);
        productInventoryList.add(new ProductInventory("montreal", 5, productList.get(0).getProductid()));
        productInventoryList.add(new ProductInventory("montreal", 5, productList.get(1).getProductid()));
        productInventoryRequestDto = new ProductInventoryRequestDto("montreal", 5,productList.get(1).getProductid());
        productInventory = new ProductInventory("montreal", 5,productList.get(1).getProductid());

        productInventoryController = new ProductInventoryController(productInventoryService, partService);
    }

    @Test
    void createProductInventory() throws Exception{
        when(productInventoryService.createProductInventory(productInventoryRequestDto)).thenReturn(productInventory);
        ResponseEntity<?> responseEntity = productInventoryController.createProductInventory(productInventoryRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void retrieveAllProductsInInventory() {
        when(productInventoryService.getAllProInv()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = productInventoryController.retrieveAllProductsInInventory();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void retrieveProductParts() {
        when(productInventoryService.getProductParts(productList.get(0).getProductid())).thenReturn(List.of());
        ResponseEntity<?> responseEntity = productInventoryController.retriveProductParts(productList.get(0).getProductid());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void updateProductInInventory() throws Exception {
        ProductInventoryRequestDto updateProductInventory = new ProductInventoryRequestDto(
                productInventoryList.get(0).getLocation(),
                100,
                productInventoryList.get(0).getProductid()

        );
        ProductInventory updatedProductInventory = new ProductInventory(
                productInventoryList.get(0).getLocation(),
                100,
                productInventoryList.get(0).getProductid()

        );

        when(productInventoryService.updateProductInInventory(productInventoryList.get(0).getId(), updateProductInventory)).thenReturn(updatedProductInventory);
        ResponseEntity<?> responseEntity = productInventoryController.editProductInfoInInventory(productInventoryList.get(0).getId(), updateProductInventory);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteProductFromInventory() throws Exception {
        when(productInventoryService.deleteProductFromInventory(productInventoryList.get(1).getId())).thenReturn("success").thenThrow(new Exception());
        ResponseEntity<?> responseEntity = productInventoryController.deleteProductInInventory(productInventoryList.get(1).getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = productInventoryController.deleteProductInInventory(productInventoryList.get(1).getId());
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

}
