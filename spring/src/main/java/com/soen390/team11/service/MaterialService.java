package com.soen390.team11.service;

import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.RawMaterial;
import com.soen390.team11.entity.MaterialRawMaterials;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.MaterialRawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialRawMaterialRepository materialrawmaterialRepository;
    @Autowired
    RawMaterialService rawmaterialService;


    public Material createMaterial(MaterialRequestDto materialRequestDto){
        return materialRepository.save(materialRequestDto.getMaterial());
    }

    public List<Material> getAllMaterial() {
        return (List<Material>) materialRepository.findAll();        
    }


    public Material getMaterialById(String id) {
        try {
            Material material= materialRepository.findById(id).get();
            return material;
        } catch (Exception e) {
            return null;
        }
    }

    public String deleteMaterial(String id) throws Exception {
        if(getMaterialById(id)==null) {
            throw new Exception("invalid id");
        }
        materialRepository.deleteById(id);
        return "success";
    }

    public Material updateMaterial(String id, MaterialRequestDto materialRequestDto) throws Exception {
        Material material = materialRequestDto.getMaterial();
        if(getMaterialById(id) ==null) {
            throw new Exception("invalid id");
        }
        material.setMaterialid(id);
        return materialRepository.save(material);
    }

    //returns every Material
    public List<Material> getAllMaterialsIninventory(){
        List<Material> Materiallist= new ArrayList<>();
        materialRepository.findAll()
                .forEach(Materiallist::add);
        return Materiallist;
    }

    //return every raw material in a material
    public List<RawMaterial> getAllMaterialRawMaterial(String materialid){
        List<RawMaterial> rawMaterial= new ArrayList<>();
        List<MaterialRawMaterials> MaterialRawMaterialsList = materialrawmaterialRepository.findByMaterialRawMaterialsIdRawmaterialid(materialid);
        for(MaterialRawMaterials materialRawMaterials : MaterialRawMaterialsList){
            rawMaterial.add(rawmaterialService.getRawMaterialById(materialRawMaterials.getMaterialRawMaterialsId().getRawmaterialid()));
        }
        return rawMaterial;
    }
}