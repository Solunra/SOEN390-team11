package com.soen390.team11.service;

import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMachineryService {
    @Autowired
    ProductMachineryRepository productMachineryRepository;

    public List<ProductMachinery> getAllMachineries() {
        return (List<ProductMachinery>) productMachineryRepository.findAll();
    }

}
