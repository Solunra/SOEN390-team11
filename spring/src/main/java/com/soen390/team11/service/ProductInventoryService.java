package com.soen390.team11.service;

import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.dto.ProductInventoryResponse;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductInventory;

import com.soen390.team11.entity.ProductParts;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductPartsRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductInventoryService {

    @Autowired
    ProductInventoryRepository productInventoryRepository;
    @Autowired
    ProductPartsRepository productPartsRepository;
    @Autowired
    PartService partService;
    @Autowired
    ProductRepository productRepository;


    public ProductInventory createProductInventory(ProductInventoryRequestDto productInventoryRequestDto) throws Exception {

       return productInventoryRepository.save(productInventoryRequestDto.getProductInventory());
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
    public ArrayList<ProductInventoryResponse> getAllProInv(){
        List<ProductInventory> productInventories= (List<ProductInventory>) productInventoryRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        ArrayList<ProductInventoryResponse> productInventoryList = new ArrayList<>();
        ProductInventoryResponse productInventoryResponse = new ProductInventoryResponse();
        for(ProductInventory pi : productInventories){
            for(Product p : products){
                if(pi.getProductid() == p.getProductid()){
                    productInventoryResponse = new ProductInventoryResponse(pi.getId(),pi.getLocation(),pi.getQuantity(),pi.getProductid(),p.getName());
                }
            }
            productInventoryList.add(productInventoryResponse);
        }
        return productInventoryList;
    }


}
