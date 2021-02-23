package com.soen390.team11.service;

import com.soen390.team11.repository.PartMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PartMaterialService {

    @Autowired
    PartMaterialRepository partMaterialRepository;

    public Map<Long, Integer> getPartMaterials(Long partId) {
        Map<Long, Integer> materialMap = new HashMap<>();
        partMaterialRepository.findAllByPartId(partId).forEach(partInventory ->
            materialMap.put(partInventory.getMaterialId(), partInventory.getMaterialQuantity())
        );
        return materialMap;
    }


}
