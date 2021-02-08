package com.soen390.team11.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.Variant;
import com.soen390.team11.repository.ProductRepository;
import com.soen390.team11.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ProductService {
    static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VariantRepository variantRepository;

    public Product createProduct(ProductRequestDto productRequestDto){
        try{
            Product product = productRequestDto.getProduct();
            checkAndCreateVariant(productRequestDto.getColor(), "color",product);
            checkAndCreateVariant(productRequestDto.getSize(), "size",product);
            checkAndCreateVariant(productRequestDto.getGrade(), "grade",product);
            checkAndCreateVariant(productRequestDto.getFinish(), "finish",product);
            product = productRepository.save(product);
            return product;
        }
        catch (Exception e){
            System.out.println("cannot create product");
            e.printStackTrace();
            return null;
        }
    }
    public void checkAndCreateVariant(ArrayList<String> variantList, String variantType, Product product){
        try{
            Variant previousVariant;
            Variant variant;
            for(int i =0;i<variantList.size();i++){
                previousVariant = variantRepository.findByValue(variantList.get(i));
                if(previousVariant ==null){
                    variant = new Variant(variantType,variantList.get(i));
                    product.addVariant(variant);
                }
                else{
                    product.addVariant(previousVariant);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public List<Product> retrieveAll(){
        List<Product> allProduct = (List<Product>) productRepository.findAll();
        return allProduct;
    }
    public Product retrieveByUUID(String UUID){
        return productRepository.findByUUID(UUID);
    }


    public String deleteByUUID(String uuid) {
        Product retrieveProduct = retrieveByUUID(uuid);
        try{
            if(retrieveProduct != null){
                productRepository.deleteById(retrieveProduct.getProductid());
            }
            return "success";
        }
        catch (Exception e){

        }
        return "not success";
    }
    public Product updateByUUID(String uuid, ProductRequestDto productRequestDto){
        System.out.println(productRequestDto);
        Product retrieveProduct = retrieveByUUID(uuid);
        try{
            if(retrieveProduct != null){
                System.out.println("retrieve product not null");
                retrieveProduct.setType(productRequestDto.getType());
                retrieveProduct.setName(productRequestDto.getName());
                retrieveProduct.setVariants(new HashSet<>());
                checkAndCreateVariant(productRequestDto.getColor(), "color",retrieveProduct);
                checkAndCreateVariant(productRequestDto.getSize(), "size",retrieveProduct);
                checkAndCreateVariant(productRequestDto.getGrade(), "grade",retrieveProduct);
                checkAndCreateVariant(productRequestDto.getFinish(), "finish",retrieveProduct);
                retrieveProduct = productRepository.save(retrieveProduct);
                return retrieveProduct;
            }
            return null;
        }
        catch (Exception e){
        }
        return null;
    }


}
