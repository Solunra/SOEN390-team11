package com.soen390.team11.service;

import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

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
        if(getProductById(id)==null) {
            throw new Exception("invalid id");
        }
        productRepository.deleteById(id);
        return "success";
    }

    public Product updateProduct(Long id, ProductRequestDto productRequestDto) throws Exception {
        Product product = productRequestDto.getProduct();
        if(getProductById(id) ==null) {
            throw new Exception("invalid id");
        }
        product.setProductid(id);
        return productRepository.save(product);
    }
}
