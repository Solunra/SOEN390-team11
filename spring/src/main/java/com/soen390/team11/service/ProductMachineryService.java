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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Service Layer for Product Machinery
 */
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

    /**
     * Gets all the product machinery
     *
     * @return List of Product Machinery
     */
    public List<ProductMachinery> getAllMachineries() {
        return (List<ProductMachinery>) productMachineryRepository.findAll();
    }
    public List<ProductMachineryDto> getAllMachineriesWithDto() {
        List<ProductMachineryDto> productMachineryDtoList = new ArrayList<>();
        Iterable<ProductMachinery> productMachineries= productMachineryRepository.findAll();
        ProductMachineryDto productMachineryDto=null;
        for(ProductMachinery pm: productMachineries){
            if(pm.getStatus().equals(MachineryState.UNASSIGNED)|| pm.getProduct() == null){
                productMachineryDto= new ProductMachineryDto(pm.getId(),pm.getName(),pm.getStatus().toString(),pm.getTimer(),
                        "empty","empty");
            }
            else{
                productMachineryDto= new ProductMachineryDto(pm.getId(),pm.getName(),pm.getStatus().toString(),pm.getTimer(),
                        pm.getProduct().getProductid(),pm.getProduct().getName());
            }
            productMachineryDtoList.add(productMachineryDto);

        }
        return productMachineryDtoList;
    }


    /**
     * Create a new Product Machinery
     *
     * @param productMachineryDto The Machine's details
     * @return New Product Machinery's ID
     */
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

    /**
     * Updates the product machinery's status
     *
     * @param machineryId The Product Machinery ID to be updated
     * @param op The Operation done on it
     * @return True if the status change was successful
     */
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

    /**
     * Search for an UNASSIGNED machinery in the list of machineries.
     *
     * @return the ID of the available machinery if found, else empty String
     */
    public String findAvailableMachinery() {
        Iterable<ProductMachinery> machineries = productMachineryRepository.findAll();
        for (ProductMachinery machinery : machineries) {
            if (machinery.getStatus().equals(MachineryState.UNASSIGNED)) {
                return machinery.getId();
            }
        }
        return "";
    }

    /**
     * Set the product to produce for a given machinery.
     *
     * @param machineryId The Product Machinery ID to be updated
     * @param productId The Product ID to be updated
     * @return Result message of the operation
     */
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
            return "Either machinery or product does not exist";
        }
    }
}
