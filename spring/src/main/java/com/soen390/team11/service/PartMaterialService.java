package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.dto.PartMaterialResponse;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.PartMaterial;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.PartMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service Layer for Part's Material
 */
@Service
public class PartMaterialService {

    PartMaterialRepository partMaterialRepository;
    MaterialRepository materialRepository;
    LogService logService;

    public PartMaterialService(PartMaterialRepository partMaterialRepository, MaterialRepository materialRepository,
                               LogService logService) {
        this.partMaterialRepository = partMaterialRepository;
        this.materialRepository = materialRepository;
        this.logService = logService;
    }

    /**
     * Map of all parts' materials and their quantity
     *
     * @param partId The part's ID
     * @return Map of all materials' and their quantity
     */
    public Map<String, Integer> getPartMaterials(String partId) {
        logService.writeLog(LogTypes.PART,"Getting all PartMaterials...");
        Map<String, Integer> materialMap = new HashMap<>();
        logService.writeLog(LogTypes.PART,"Finding all parts by ID");
        partMaterialRepository.findAllByPartId(partId).forEach(
                partInventory -> materialMap.put(partInventory.getMaterialId(), partInventory.getmaterialQuantity()));
        logService.writeLog(LogTypes.PART,"Returning parts");
        return materialMap;
    }

    /**
     * Gets all Materials of a Part
     *
     * @param partId The Part
     * @return The Part's materials
     */
    public List<PartMaterialResponse> getAllMaterialsOfPart(String partId) {
        logService.writeLog(LogTypes.PART,"Getting all Materials of part");
        List<PartMaterial> partMaterialList = partMaterialRepository.findAllByPartId(partId);
        List<Material> materials = (List<Material>) materialRepository.findAll();
        List<PartMaterialResponse> PartMaterialResponselist = new ArrayList<>();
        PartMaterialResponse partMaterialResponse = new PartMaterialResponse();
        logService.writeLog(LogTypes.PART,"Finding all materials by parts");
        for (PartMaterial pm : partMaterialList) {
            for (Material m : materials) {
                if (pm.getMaterialId().equals(m.getMaterialid())) {
                    partMaterialResponse = new PartMaterialResponse(pm.getId(), pm.getPartId(), pm.getMaterialId(),
                            pm.getmaterialQuantity(), m.getName());
                }
            }
            PartMaterialResponselist.add(partMaterialResponse);
        }
        logService.writeLog(LogTypes.PART,"Returning Materials");
        return PartMaterialResponselist;
    }

}
