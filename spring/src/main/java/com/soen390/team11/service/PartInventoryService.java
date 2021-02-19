package com.soen390.team11.service;

import com.soen390.team11.repository.PartInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartInventoryService {

    @Autowired
    PartInventoryRepository partInventoryRepository;

    public Map<Long, Integer> getPartMaterials(Long partId) {
        Map<Long, Integer> materialMap = new HashMap<>();
        partInventoryRepository.findAllByPartId(partId).forEach(partInventory ->
            materialMap.put(partInventory.getMaterialId(), partInventory.getMaterialQuantity())
        );
        return materialMap;
    }


}
