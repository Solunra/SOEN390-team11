package com.soen390.team11.service;

import com.soen390.team11.entity.Part;
import com.soen390.team11.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    @Autowired
    PartRepository partRepository;



    public List<Part> getAllPart() {
        List<Part> parts = new ArrayList<>();
        partRepository.findAll()
                .forEach(parts::add);
        return parts;
    }


    public Part getPartById(Long id) {
        try {
            Part part = partRepository.findById(id).get();
            return part;
        } catch (Exception e) {
            return null;

        }
    }



}