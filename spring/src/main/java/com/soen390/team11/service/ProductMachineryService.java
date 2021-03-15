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

/**
 * Service Layer for Product Machinery
 */
@Service
public class ProductMachineryService {

    @Autowired
    ProductMachineryRepository productMachineryRepository;

    @Autowired
    ProductRepository productRepository;

    /**
     * Gets all the product machinery
     *
     * @return List of Product Machinery
     */
    public List<ProductMachinery> getAllMachineries() {
        return (List<ProductMachinery>) productMachineryRepository.findAll();
    }

    /**
     * Create a new Product Machinery
     *
     * @param productMachineryDto The Machine's details
     * @return New Product Machinery's ID
     */
    public String createMachinery(ProductMachineryDto productMachineryDto) {
        Optional<Product> optionalProduct = productRepository
            .findById(productMachineryDto.getProductId());
        if (optionalProduct.isPresent()) {
            ProductMachinery newMachinery = new ProductMachinery(productMachineryDto.getName(),
                MachineryState.valueOf(productMachineryDto.getStatus().toUpperCase()), productMachineryDto.getTimer(),
                optionalProduct.get());
            productMachineryRepository.save(newMachinery);
            return newMachinery.getId();
        } else {
            return "";
        }
    }

    /**
     * Updates the product machinery's status
     *
     * @param machineryId The Product Machinery ID to be updated
     * @param op The Operation done on it
     * @return True if the status change was successful
     */
    public boolean updateMachineryStatus(String machineryId, String op) {
        Optional<ProductMachinery> optionalProductMachinery = productMachineryRepository
            .findById(machineryId);

        if (optionalProductMachinery.isPresent()) {

            // search for the requested operation in the defined operation enum class
            for (MachineryOp definedOp : MachineryOp.values()) {

                // if found, validate the transition using setStatus
                if (definedOp.toString().equals(op.toUpperCase())) {
                    if (optionalProductMachinery.get().setStatus(MachineryOp.getTransitionState(definedOp))) {
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
