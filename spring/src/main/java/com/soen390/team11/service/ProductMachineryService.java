package com.soen390.team11.service;

import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMachineryService {

    @Autowired
    ProductMachineryRepository productMachineryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<ProductMachinery> getAllMachineries() {
        return (List<ProductMachinery>) productMachineryRepository.findAll();
    }

    public String createMachinery(ProductMachineryDto productMachineryDto) {
        Optional<Product> optionalProduct = productRepository
            .findById(productMachineryDto.getProductId());
        if (optionalProduct.isPresent()) {
            ProductMachinery newMachinery = new ProductMachinery(productMachineryDto.getName(),
                productMachineryDto.getStatus(), productMachineryDto.getTimer(),
                optionalProduct.get());
            productMachineryRepository.save(newMachinery);
            return newMachinery.getId();
        } else {
            return "";
        }
    }

    public boolean updateMachineryStatus(String machineryId, String status) {
        Optional<ProductMachinery> optionalProductMachinery = productMachineryRepository
            .findById(machineryId);
        if (optionalProductMachinery.isPresent()) {
            optionalProductMachinery.get().setStatus(status);
            return true;
        } else {
            return false;
        }
    }
}
