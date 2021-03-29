package com.soen390.team11.service;

import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductInventory;
import com.soen390.team11.repository.PartRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service Layer for Product
 */
@Service
public class ProductService {

    ProductRepository productRepository;
    PartRepository partRepository;
    ProductInventoryRepository productInventoryRepository;

    public ProductService(ProductRepository productRepository, PartRepository partRepository, ProductInventoryRepository productInventoryRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
        this.productInventoryRepository = productInventoryRepository;
    }

    /**
     * Create a new Product
     *
     * @param productRequestDto New Product's details
     * @return
     */
    public Product createProduct(ProductRequestDto productRequestDto){
        Optional<Product> product = productRepository.findByNameAndTypeAndSizeAndColorAndFinishAndGrade(productRequestDto.getName(),productRequestDto.getType(),
                productRequestDto.getSize(),productRequestDto.getColor(),productRequestDto.getFinish(),
                productRequestDto.getGrade());
        if(Optional.empty().isEmpty()){
            return productRepository.save(productRequestDto.getProduct());
        }
        return new Product();

    }

    /**
     * Gets all products
     *
     * @return List of all products
     */
    public List<Product> getAllProduct(){
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Gets a product
     *
     * @param id The product's ID
     * @return The product's details
     */
    public Product getProductById(String id){
        try{
            Product product= productRepository.findById(id).get();
            return product;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * Deletes a product
     *
     * @param id The Product's ID
     * @return Success message
     * @throws Exception Thrown if the id does not exist or violates a policy
     */
    public String deleteProduct(String id) throws Exception {
        if(checkInventory(id)){
            throw new Exception("cannot delete product, already product in inventory");
        }
        if(getProductById(id)==null) {
            throw new Exception("invalid id");
        }
        productRepository.deleteByProductid(id);
        return "success";
    }

    /**
     * Updates a product
     *
     * @param id Product's ID
     * @param productRequestDto Product's Details
     * @return The updated Product's details
     * @throws Exception Thrown if the id does not exist or violates a policy
     */
    public Product updateProduct(String id, ProductRequestDto productRequestDto) throws Exception {
        if(checkInventory(id)){
            throw new Exception("cannot edit product, already produced in inventory");
        }
        Product product = productRequestDto.getProduct();
        if(getProductById(id) ==null) {
            throw new Exception("invalid id");
        }
        product.setProductid(id);
        return productRepository.save(product);
    }

    /**
     * Gets all product parts
     *
     * @return List of parts of the product
     */
    public List<Part> getAllProductPart() {
        List<Part> allParts = (List<Part>) partRepository.findAll();
        return allParts;
    }

    /**
     * Verifies if the product exists in inventory
     *
     * @param id The Product's ID
     * @return True if the product exists in inventory
     */
    public boolean checkInventory(String id){
        ProductInventory productInventory =  productInventoryRepository.findByProductid(id);
        return productInventory !=null;
    }
}
