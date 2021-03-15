package com.soen390.team11.service;

import com.soen390.team11.dto.PartMaterialResponse;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.PartMaterial;
import com.soen390.team11.entity.ProductParts;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.PartMaterialRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductPartsRepository;
import com.soen390.team11.dto.ProductRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductPartService {

    @Autowired
    ProductPartsRepository productPartsRepository;

    public ProductParts createProductParts(ProductRequestDto productRequestDto){
     return productPartsRepository.save(productRequestDto);
    }

}
