package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.entity.Log;
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
    LogService logService;

    public PartService(PartRepository partRepository, LogService logService) {
        this.partRepository = partRepository;
        this.logService = logService;
    }

    /**
     * Gets all Parts
     *
     * @return List of all Parts
     */
    public List<Part> getAllPart() {
        logService.writeLog(LogTypes.PART,"getting all parts");
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
            logService.writeLog(LogTypes.PART,"Finding part by ID");
            Part part = partRepository.findById(id).get();
            return part;
        } catch (Exception e) {
            return null;

        }
    }



}