package com.soen390.team11.service;

import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.ProductInventory;

import com.soen390.team11.entity.ProductParts;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInventoryService {

    @Autowired
    ProductInventoryRepository productInventoryRepository;
    @Autowired
    ProductPartsRepository productPartsRepository;
    @Autowired
    PartService partService;


    public ProductInventory createProductInventory(ProductInventoryRequestDto productInventoryRequestDto) throws Exception {

       return productInventoryRepository.save(productInventoryRequestDto.getProductInventory());
    }

    public List<ProductInventory> getAllProductsInInventory(){
        List<ProductInventory> productInventories= new ArrayList<>();
        productInventoryRepository.findAll()
                .forEach(productInventories::add);
        return productInventories;
    }

    public ProductInventory getProductInventoryByID(Long id){
        try{
            ProductInventory productInventory= productInventoryRepository.findById(id).get();
            return productInventory;
        }
        catch (Exception e){
            return null;
        }
    }

    public String deleteProductFromInventory(Long id) throws Exception {
        if(getProductInventoryByID(id)==null) {
            throw new Exception("invalid id");
        }
        productInventoryRepository.deleteById(id);
        return "success";
    }

    public ProductInventory updateProductInInventory(Long id, ProductInventoryRequestDto productInventoryRequestDto) throws Exception {
        ProductInventory productInventory = productInventoryRequestDto.getProductInventory();
        if(getProductInventoryByID(id) ==null) {
            throw new Exception("invalid id");
        }
        productInventory.setId(id);
        return productInventoryRepository.save(productInventory);
    }

    public List<Part> getProductParts(Long productid){
        List<Part> parts= new ArrayList<>();
        List<ProductParts> productPartsList = productPartsRepository.findByProductPartsIdProductid(productid);
        for(ProductParts productParts : productPartsList){
            parts.add(partService.getPartById(productParts.getProductPartsId().getPartid()));
        }
        return parts;
    }


}
