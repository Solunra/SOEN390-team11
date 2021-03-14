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

    ProductMachineryRepository productMachineryRepository;

    ProductRepository productRepository;

    @Autowired
    public ProductMachineryService(
        ProductMachineryRepository productMachineryRepository,
        ProductRepository productRepository) {
        this.productMachineryRepository = productMachineryRepository;
        this.productRepository = productRepository;
    }

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

    public String updateMachineryStatus(String machineryId, String op) {
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
                        return "Success";
                    }
                    return "State transition is invalid";
                }
            }

            return "Operation is not supported";
        } else {
            return "Machinery does not exist";
        }
    }

    public String findAvailableMachinery() {
        Iterable<ProductMachinery> machineries = productMachineryRepository.findAll();
        for (ProductMachinery machinery : machineries) {
            if (machinery.getStatus().equals(MachineryState.UNASSIGNED)) {
                return machinery.getId();
            }
        }
        return "";
    }

    public String occupyMachinery(String machineryId, String productId) {
        Optional<ProductMachinery> optionalProductMachinery = productMachineryRepository
            .findById(machineryId);

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProductMachinery.isPresent() && optionalProduct.isPresent()) {

            if (optionalProductMachinery.get().setProduct(optionalProduct.get())) {

                optionalProductMachinery.get()
                    .setTimer(50); // FIXME replace fixed time with specific time for each machine

                optionalProductMachinery.get().setStatus(MachineryState.READY);

                productMachineryRepository.save(optionalProductMachinery.get());
                return "Success";

            } else {
                return "Machine is unavailable";
            }
        } else {
            if (optionalProductMachinery.isPresent()) return "PRODUCT!";
            else return "MACHINE!";
//            return "Either machinery or product does not exist";
        }
    }
}
