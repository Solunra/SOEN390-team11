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
import java.util.List;

/**
 * Service Layer for Product's Inventory
 */
@Service
public class ProductInventoryService {

    ProductInventoryRepository productInventoryRepository;
    ProductPartsRepository productPartsRepository;
    PartService partService;
    ProductRepository productRepository;

    public ProductInventoryService(ProductInventoryRepository productInventoryRepository, ProductPartsRepository productPartsRepository, PartService partService, ProductRepository productRepository) {
        this.productInventoryRepository = productInventoryRepository;
        this.productPartsRepository = productPartsRepository;
        this.partService = partService;
        this.productRepository = productRepository;
    }

    /**
     * Create a new Product's Inventory
     *
     * @param productInventoryRequestDto The new Product's Inventory details
     * @return The new Product Inventory
     * @throws Exception Thrown when there's an issue with the DB
     */
    public ProductInventory createProductInventory(ProductInventoryRequestDto productInventoryRequestDto) throws Exception {
       return productInventoryRepository.save(productInventoryRequestDto.getProductInventory());
    }

    /**
     * Get a specific Product's inventory
     *
     * @param id The product's ID
     * @return The Product's inventory
     */
    public ProductInventory getProductInventoryByID(String id){
        try{
            ProductInventory productInventory= productInventoryRepository.findById(id).get();
            return productInventory;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * Delete a Product's Inventory
     *
     * @param id The Product's ID
     * @return Success string
     * @throws Exception Thrown when the ID is not linked to an inventory
     */
    public String deleteProductFromInventory(String id) throws Exception {
        if(getProductInventoryByID(id)==null) {
            throw new Exception("invalid id");
        }
        productInventoryRepository.deleteById(id);
        return "success";
    }

    /**
     * Updates a Product Inventory
     *
     * @param id Product's inventory ID
     * @param productInventoryRequestDto Product Inventory's updated details
     * @return The Product Inventory Details
     * @throws Exception Thrown when the ID is not linked to an inventory
     */
    public ProductInventory updateProductInInventory(String id, ProductInventoryRequestDto productInventoryRequestDto) throws Exception {
        ProductInventory productInventory = productInventoryRequestDto.getProductInventory();
        if(getProductInventoryByID(id) ==null) {
            throw new Exception("invalid id");
        }
        productInventory.setId(id);
        return productInventoryRepository.save(productInventory);
    }

    /**
     * Gets the Product's parts
     *
     * @param productid The Product's part
     * @return List of Parts
     */
    public List<Part> getProductParts(String productid){
        List<Part> parts= new ArrayList<>();
        List<ProductParts> productPartsList = productPartsRepository.findByProductPartsIdProductid(productid);
        for(ProductParts productParts : productPartsList){
            parts.add(partService.getPartById(productParts.getProductPartsId().getPartid()));
        }
        return parts;
    }

    /**
     * Gets all Product Inventory
     *
     * @return List of Product's Inventories
     */
    public List<ProductInventoryResponse> getAllProInv(){
        List<ProductInventory> productInventories= (List<ProductInventory>) productInventoryRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        ArrayList<ProductInventoryResponse> productInventoryList = new ArrayList<>();
        ProductInventoryResponse productInventoryResponse = new ProductInventoryResponse();
        for(ProductInventory pi : productInventories){
            for(Product p : products){
                if(pi.getProductid().equals(p.getProductid())){
                    productInventoryResponse = new ProductInventoryResponse(pi.getId(),pi.getLocation(),pi.getQuantity(),pi.getProductid(),p.getName());
                }
            }
            productInventoryList.add(productInventoryResponse);
        }
        return productInventoryList;
    }
}
