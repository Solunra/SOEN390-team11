package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.PartRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductPartsRepository;
import com.soen390.team11.repository.ProductRepository;
import org.junit.jupiter.api.*;
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

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductInventoryControllerTest {

    @Autowired
    ProductInventoryController productInventoryController;
    @Autowired
    PartRepository partRepository;
    @Autowired
    ProductPartsRepository productPartsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductInventoryRepository productInventoryRepository;
    ProductInventoryRequestDto productInventoryRequestDto;

    List<ProductInventory> productInventoryList = new ArrayList<>();
    List<Part> partList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<ProductParts> productPartsList = new ArrayList<>();
    ObjectMapper objectMapper= new ObjectMapper();

    @BeforeAll
    public  void setup(){
        Product product1 = new Product("bike1","mountain","medium","black","matte","A");
        Product product2 = new Product("bike2","road","large","red","matte","A");
        productList.add(productRepository.save(product1));
        productList.add(productRepository.save(product2));
        Part part1 = new Part("part1");
        Part part2 = new Part("part2");
        partList.add(partRepository.save(part1));
        partList.add(partRepository.save(part2));
        ProductPartsId productPartsId1 = new ProductPartsId(partList.get(0).getPartid(),productList.get(0).getProductid());
        ProductPartsId productPartsId2 = new ProductPartsId(partList.get(1).getPartid(),productList.get(0).getProductid());
        ProductParts productParts1 = new ProductParts(productPartsId1);
        ProductParts productParts2 = new ProductParts(productPartsId2);
        productPartsList.add(productPartsRepository.save(productParts1));
        productPartsList.add(productPartsRepository.save(productParts2));
        productInventoryList.add(productInventoryRepository.save(new ProductInventory("montreal", 5,productList.get(0).getProductid())));
        productInventoryRequestDto = new ProductInventoryRequestDto("montreal", 5,productList.get(1).getProductid());
    }

    @Test
    @Order(1)
    void createProductInventory() throws Exception{
        ResponseEntity<?> responseEntity = productInventoryController.createProductInventory(productInventoryRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Map<String,Object> responseBody = objectMapper.readValue(responseEntity.getBody().toString(),Map.class);
        productInventoryList.add(new ProductInventory(responseBody.get("location").toString(),Integer.parseInt(responseBody.get("quantity").toString()), responseBody.get("productid").toString()));
        productInventoryList.get(1).setId(responseBody.get("id").toString());
    }

    @Test
    @Order(2)
    void retrieveAllProductsInInventory() {
        ResponseEntity<?> responseEntity = productInventoryController.retrieveAllProductsInInventory();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Order(3)
    void retrieveProductParts() {
        ResponseEntity<?> responseEntity = productInventoryController.retriveProductParts(productList.get(0).getProductid());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Order(4)
    void updateProductInInventory() {
        ProductInventoryRequestDto updateProductInventory = new ProductInventoryRequestDto(
                productInventoryList.get(0).getLocation(),
                100,
                productInventoryList.get(0).getProductid()

        );
        ResponseEntity<?> responseEntity = productInventoryController.editProductInfoInInventory(productInventoryList.get(0).getId() ,updateProductInventory );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(5)
    void deleteProductFromInventory() {
        ResponseEntity<?> responseEntity = productInventoryController.deleteProductInInventory(productInventoryList.get(1).getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = productInventoryController.deleteProductInInventory(productInventoryList.get(1).getId());
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @AfterAll
    public void cleanup(){

        for (ProductParts productParts:productPartsList){
            productPartsRepository.delete(productParts);
        }
        for(Part part:partList){
            partRepository.delete(part);
        }
        for(ProductInventory productInventory:productInventoryList){
            productInventoryRepository.delete(productInventory);
        }
        for(Product product: productList){
            productRepository.delete(product);
        }

    }
}
