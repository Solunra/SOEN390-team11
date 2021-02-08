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

/**
 * service class
 * to provide all the action that perform
 */
@Service
public class ProductService {
    static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VariantRepository variantRepository;

    /**
     * create the product
     * @param productRequestDto
     * @return
     */
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

    /**
     * check the variant is already have or not
     * if not create new variant and associate with the product
     * if already have just add to the list of variant of the product
     * @param variantList
     * @param variantType
     * @param product
     */
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

    /**
     * retrieve all the product in the database
     * if error occur return null
     * @return
     */
    public List<Product> retrieveAll(){
        try{
            List<Product> allProduct = (List<Product>) productRepository.findAll();
            return allProduct;
        }
        catch (Exception e){
            return null;
        }

    }

    /**
     * to retrieve product by the UUID
     * if error occur return null
     * @param UUID
     * @return
     */
    public Product retrieveByUUID(String UUID){
        try{
            return productRepository.findByUUID(UUID);
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * delete by the UUID
     * if error occur return String not success
     * @param uuid
     * @return
     */
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

    /**
     * update by the uuid
     * @param uuid
     * @param productRequestDto
     * @return update product
     * or return null if error occur
     */
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
