package com.soen390.team11.service;

import com.soen390.team11.constant.MachineryOp;
import com.soen390.team11.constant.MachineryState;
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

        // if no product is bound
        if (productMachineryDto.getProductId().trim().isEmpty()) {
            return productMachineryRepository
                .save(new ProductMachinery(productMachineryDto.getName(), MachineryState.UNASSIGNED,
                    0, null)).getId();
        }

        // if product is bound
        Optional<Product> optionalProduct = productRepository
            .findById(productMachineryDto.getProductId());
        if (optionalProduct.isPresent()) {
            ProductMachinery newMachinery = new ProductMachinery(productMachineryDto.getName(),
                MachineryState.valueOf(productMachineryDto.getStatus().toUpperCase()),
                productMachineryDto.getTimer(),
                optionalProduct.get());
            return productMachineryRepository.save(newMachinery).getId();
        }

        // if product is invalid
        return "";
    }

    public boolean updateMachineryStatus(String machineryId, String op) {
        Optional<ProductMachinery> optionalProductMachinery = productMachineryRepository
            .findById(machineryId);

        if (optionalProductMachinery.isPresent()) {

            // search for the requested operation in the defined operation enum class
            for (MachineryOp definedOp : MachineryOp.values()) {

                // if found, validate the transition using setStatus
                if (definedOp.toString().equals(op.toUpperCase())) {
                    if (optionalProductMachinery.get()
                        .setStatus(MachineryOp.getTransitionState(definedOp))) {
                        productMachineryRepository.save(optionalProductMachinery.get());
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
