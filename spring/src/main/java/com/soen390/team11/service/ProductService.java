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
        try{
            return productRepository.save(productRequestDto.getProduct());
        }
        catch (Exception e){
            return null;
        }
    }

    public List<Product> getAllProduct(){
        try{
            return (List<Product>) productRepository.findAll();
        }
        catch (Exception e){
            return null;
        }
    }

    public Product getProductById(Long id){
        try{
            return productRepository.findById(id).get();
        }
        catch (Exception e){
            return null;
        }
    }

    public String deleteProduct(Long id){
        try{
            productRepository.deleteById(id);
            return "success";
        }
        catch (Exception e){
            return "cannot delete";
        }
    }

    public Product updateProduct(Long id, ProductRequestDto productRequestDto){
        try{
            Product product = productRequestDto.getProduct();
            product.setProductid(id);
            return productRepository.save(product);
        }
        catch (Exception e){
            return null;
        }
    }
}
