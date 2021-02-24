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

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PartRepository partRepository;
    @Autowired
    ProductInventoryRepository productInventoryRepository;

    public Product createProduct(ProductRequestDto productRequestDto){
        return productRepository.save(productRequestDto.getProduct());
    }

    public List<Product> getAllProduct(){
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(Long id){
        try{
            Product product= productRepository.findById(id).get();
            return product;
        }
        catch (Exception e){
            return null;
        }
    }

    public String deleteProduct(Long id) throws Exception {
        if(checkInventory(id)){
            throw new Exception("cannot delete product, already product in inventory");
        }
        if(getProductById(id)==null) {
            throw new Exception("invalid id");
        }
        productRepository.deleteById(id);
        return "success";
    }

    public Product updateProduct(Long id, ProductRequestDto productRequestDto) throws Exception {
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

    public List<Part> getAllProductPart() {
        List<Part> allParts = (List<Part>) partRepository.findAll();
        return allParts;
    }
    public boolean checkInventory(Long id){
        ProductInventory productInventory =  productInventoryRepository.findByProductid(id);
        return productInventory !=null;
    }
}
