package com.soen390.team11.service;

import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.entity.RawMaterial;
import com.soen390.team11.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    RawMaterialRepository rawmaterialRepository;




    public List<RawMaterial> getAllRawMaterial() {
        return (List<RawMaterial>) rawmaterialRepository.findAll();
//        List<RawMaterial> RawMaterials = new ArrayList<>();
//        rawmaterialRepository.findAll()
//                .forEach(RawMaterials::add);
//        return RawMaterials;
    }


    public RawMaterial getRawMaterialById(String id) {
        try {
            RawMaterial rawmaterial= rawmaterialRepository.findById(id).get();
            return rawmaterial;
        } catch (Exception e) {
            return null;

        }
    }

    public String createNewRawMaterial(RawMaterialRequestDto rawMaterialRequestDto){
        RawMaterial newRawMaterial = new RawMaterial(
                rawMaterialRequestDto.getname(),
                rawMaterialRequestDto.getDescription(),
                rawMaterialRequestDto.getPrice(),
                rawMaterialRequestDto.getUnit()
        );
        String rawMaterialidID = rawmaterialRepository.save(newRawMaterial).getrawmaterialid();
        return rawMaterialidID;
    }


}