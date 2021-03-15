package com.soen390.team11.service;

import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.MaterialRawMaterials;
import com.soen390.team11.repository.MaterialRawMaterialRepository;
import com.soen390.team11.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Layer for Material
 */
@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialRawMaterialRepository materialrawmaterialRepository;
    @Autowired
    RawMaterialService rawmaterialService;

    /**
     * Creates a new Material
     *
     * @param materialRequestDto The Material Request
     * @return The created Material
     */
    public Material createMaterial(MaterialRequestDto materialRequestDto){
        return materialRepository.save(materialRequestDto.getMaterial());
    }

    /**
     * Gets all Materials
     *
     * @return List of Materials
     */
    public List<Material> getAllMaterial() {
        return (List<Material>) materialRepository.findAll();        
    }

    /**
     * Gets a specific Material
     *
     * @param id Material's ID
     * @return The Material
     */
    public Material getMaterialById(String id) {
        try {
            Material material= materialRepository.findById(id).get();
            return material;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Deletes a Material
     *
     * @param id The Material's ID
     * @return success message
     * @throws Exception Thrown exception if there is an invalid ID
     */
    public String deleteMaterial(String id) throws Exception {
        if(getMaterialById(id)==null) {
            throw new Exception("invalid id");
        }
        materialRepository.deleteById(id);
        return "success";
    }

    /**
     * Update a Material
     *
     * @param id The Material's ID
     * @param materialRequestDto The Material's Details
     * @return The updated Material
     * @throws Exception Thrown exception if there is an invalid ID
     */
    public Material updateMaterial(String id, MaterialRequestDto materialRequestDto) throws Exception {
        Material material = materialRequestDto.getMaterial();
        if(getMaterialById(id) ==null) {
            throw new Exception("invalid id");
        }
        material.setMaterialid(id);
        return materialRepository.save(material);
    }

    /**
     * Gets All Materials
     *
     * @return List of all Materials
     */
    public List<Material> getAllMaterialsIninventory(){
        List<Material> Materiallist= new ArrayList<>();
        materialRepository.findAll()
                .forEach(Materiallist::add);
        return Materiallist;
    }

    /**
     * Gets list of Material's Raw Materials
     *
     * @param materialid The Material's ID
     * @return List of Raw Materials
     */
    public List<RawMaterialRequestDto> getAllMaterialRawMaterial(String materialid){
        List<RawMaterialRequestDto> rawMaterial= new ArrayList<>();
        List<MaterialRawMaterials> MaterialRawMaterialsList = materialrawmaterialRepository.findByMaterialRawMaterialsIdRawmaterialid(materialid);
        for(MaterialRawMaterials materialRawMaterials : MaterialRawMaterialsList){
            rawMaterial.add(rawmaterialService.getRawMaterialById(materialRawMaterials.getMaterialRawMaterialsId().getRawmaterialid()));
        }
        return rawMaterial;
    }
}