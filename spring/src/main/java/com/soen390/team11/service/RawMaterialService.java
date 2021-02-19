package com.soen390.team11.service;

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
        List<RawMaterial> RawMaterials = new ArrayList<>();
        rawmaterialRepository.findAll()
                .forEach(RawMaterials::add);
        return RawMaterials;
    }


    public RawMaterial getRawMaterialById(Long id) {
        try {
            RawMaterial rawmaterial= rawmaterialRepository.findById(id).get();
            return rawmaterial;
        } catch (Exception e) {
            return null;

        }
    }



}