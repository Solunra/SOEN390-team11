package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
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
    LogService logService;

    public ProductMachineryService(ProductMachineryRepository productMachineryRepository, ProductRepository productRepository, LogService logService) {
        this.productMachineryRepository = productMachineryRepository;
        this.productRepository = productRepository;
        this.logService = logService;
    }

    /**
     * Gets all the product machinery
     *
     * @return List of Product Machinery
     */
    public List<ProductMachinery> getAllMachineries() {
        logService.writeLog(LogTypes.MACHINERY,"Getting all products in the machinery");
        return (List<ProductMachinery>) productMachineryRepository.findAll();
    }
    public List<ProductMachineryDto> getAllMachineriesWithDto() {
        logService.writeLog(LogTypes.VENDOR,"Getting all machineries with a Dto");
        List<ProductMachineryDto> productMachineryDtoList = new ArrayList<>();
        logService.writeLog(LogTypes.VENDOR,"Finding all Machines");
        Iterable<ProductMachinery> productMachineries= productMachineryRepository.findAll();
        ProductMachineryDto productMachineryDto=null;
        for(ProductMachinery pm: productMachineries){
            if(pm.getStatus().equals(MachineryState.UNASSIGNED)|| pm.getProduct() == null){
                productMachineryDto= new ProductMachineryDto(pm.getId(),pm.getName(),pm.getStatus().toString(),0,
                        "empty","empty");
            }
            else{
                productMachineryDto= new ProductMachineryDto(pm.getId(),pm.getName(),pm.getStatus().toString(),pm.getTimer(),
                        pm.getProduct().getProductid(),pm.getProduct().getName());
            }
            logService.writeLog(LogTypes.VENDOR,"Adding Machine to the DtoList");
            productMachineryDtoList.add(productMachineryDto);

        }
        logService.writeLog(LogTypes.VENDOR,"Returning the List");
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
            logService.writeLog(LogTypes.VENDOR,"No product is bound");
            logService.writeLog(LogTypes.VENDOR,"Creating a product machinery");
            return productMachineryRepository
                .save(new ProductMachinery(productMachineryDto.getName(), MachineryState.UNASSIGNED,
                    0, null)).getId();
        }

        // if product is bound
        logService.writeLog(LogTypes.VENDOR,"Product is bound");
        Optional<Product> optionalProduct = productRepository
            .findById(productMachineryDto.getProductId());
        if (optionalProduct.isPresent()) {
            ProductMachinery newMachinery = new ProductMachinery(productMachineryDto.getName(),
                MachineryState.valueOf(productMachineryDto.getStatus().toUpperCase()),
                productMachineryDto.getTimer(),
                optionalProduct.get());
            logService.writeLog(LogTypes.VENDOR,"Creating a product machinery");
            return productMachineryRepository.save(newMachinery).getId();
        }

        // if product is invalid
        logService.writeLog(LogTypes.VENDOR,"Product is invalid");
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
            logService.writeLog(LogTypes.VENDOR,"searching for the requested operation in the defined operation enum class");
            // search for the requested operation in the defined operation enum class
            for (MachineryOp definedOp : MachineryOp.values()) {

                // if found, validate the transition using setStatus
                logService.writeLog(LogTypes.VENDOR,"validating the transition using setStatus");
                if (definedOp.toString().equals(op.toUpperCase())) {
                    if (optionalProductMachinery.get()
                        .setStatus(MachineryOp.getTransitionState(definedOp))) {

                        // if status is unassigned, set the timer to 0
                        if (optionalProductMachinery.get()
                            .getStatus() == MachineryState.UNASSIGNED) {
                            logService.writeLog(LogTypes.VENDOR,"Status is unassigned setting the timer to 0");
                            optionalProductMachinery.get().setTimer(0);
                        }

                        //if status is running and timer is 0, set status to ready
                        if (optionalProductMachinery.get().getStatus().equals(MachineryState.RUNNING)
                            && optionalProductMachinery.get().getTimer() == 0) {
                            logService.writeLog(LogTypes.VENDOR,"Setting the status to ready");
                            optionalProductMachinery.get().setStatus(MachineryState.READY);
                        }
                        productMachineryRepository.save(optionalProductMachinery.get());
                        logService.writeLog(LogTypes.VENDOR,"Optional product successfully saved");
                        return "Success";
                    }
                    logService.writeLog(LogTypes.VENDOR,"State transition is invalid");
                    return "State transition is invalid";
                }
            }
            logService.writeLog(LogTypes.VENDOR,"Operation was not supported");
            return "Operation is not supported";
        } else {
            logService.writeLog(LogTypes.VENDOR,"Machinery did not exist");
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
        logService.writeLog(LogTypes.VENDOR,"Searching for an UNASSIGNED machinery in the list of machineries.");
        for (ProductMachinery machinery : machineries) {
            if (machinery.getStatus().equals(MachineryState.UNASSIGNED)) {
                logService.writeLog(LogTypes.VENDOR,"Returning machinery ID");
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
                logService.writeLog(LogTypes.VENDOR,"Setting the product to produce for a given machinery.");
                optionalProductMachinery.get()
                    .setTimer(50); // FIXME replace fixed time with specific time for each machine
                logService.writeLog(LogTypes.VENDOR,"Setting Machinery State to ready");
                optionalProductMachinery.get().setStatus(MachineryState.READY);

                productMachineryRepository.save(optionalProductMachinery.get());
                logService.writeLog(LogTypes.VENDOR,"Successfully set the machine");
                return "Success";

            } else {
                logService.writeLog(LogTypes.VENDOR,"Machine was not available");
                return "Machine is unavailable";
            }
        } else {
            logService.writeLog(LogTypes.VENDOR,"Machine or product does not exist");
            return "Either machinery or product does not exist";
        }
    }
}
