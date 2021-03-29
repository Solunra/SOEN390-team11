package com.soen390.team11.service;

import com.soen390.team11.entity.Part;
import com.soen390.team11.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Layer for Part
 */
@Service
public class PartService {

    PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    /**
     * Gets all Parts
     *
     * @return List of all Parts
     */
    public List<Part> getAllPart() {
        List<Part> parts = new ArrayList<>();
        partRepository.findAll()
                .forEach(parts::add);
        return parts;
    }

    /**
     * Gets a Part
     *
     * @param id The Part's ID
     * @return The Part's Details
     */
    public Part getPartById(String id) {
        try {
            Part part = partRepository.findById(id).get();
            return part;
        } catch (Exception e) {
            return null;

        }
    }



}