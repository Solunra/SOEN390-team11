package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
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
    LogService logService;

    public ProductInventoryService(ProductInventoryRepository productInventoryRepository, ProductPartsRepository productPartsRepository, PartService partService, ProductRepository productRepository
    , LogService logService) {
        this.productInventoryRepository = productInventoryRepository;
        this.productPartsRepository = productPartsRepository;
        this.partService = partService;
        this.productRepository = productRepository;
        this.logService = logService;
    }

    /**
     * Create a new Product's Inventory
     *
     * @param productInventoryRequestDto The new Product's Inventory details
     * @return The new Product Inventory
     * @throws Exception Thrown when there's an issue with the DB
     */
    public ProductInventory createProductInventory(ProductInventoryRequestDto productInventoryRequestDto) throws Exception {
        logService.writeLog(LogTypes.VENDOR,"Creating product inventory");
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
            logService.writeLog(LogTypes.VENDOR,"Searching for product by ID");
            ProductInventory productInventory= productInventoryRepository.findById(id).get();
            return productInventory;
        }
        catch (Exception e){
            logService.writeLog(LogTypes.VENDOR,"Product does not exist");
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
        logService.writeLog(LogTypes.VENDOR,"Deleting product using ID");
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
        logService.writeLog(LogTypes.VENDOR,"Updating product ID and saving it");
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
        logService.writeLog(LogTypes.VENDOR,"Finding every parts in a product");
        List<ProductParts> productPartsList = productPartsRepository.findByProductPartsIdProductid(productid);
        logService.writeLog(LogTypes.VENDOR,"Going through the product parts list");
        for(ProductParts productParts : productPartsList){
            logService.writeLog(LogTypes.VENDOR,"Adding part by ID");
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
        logService.writeLog(LogTypes.VENDOR,"Getting all products in the inventory...");
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
            logService.writeLog(LogTypes.VENDOR,"Adding product to the inventory list");
            productInventoryList.add(productInventoryResponse);
        }
        return productInventoryList;
    }
}
