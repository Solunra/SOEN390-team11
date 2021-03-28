package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.service.PartMaterialService;
import com.soen390.team11.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for part's materials
 */
@RestController
@RequestMapping("/parts")
public class PartMaterialController {

    ObjectMapper objectMapper= new ObjectMapper();

    PartMaterialService partMaterialService;
    PartService partService;

    public PartMaterialController(PartMaterialService partMaterialService, PartService partService) {
        this.partMaterialService = partMaterialService;
        this.partService = partService;
    }

    /**
     * Gets the amount of material that consists a specific part
     *
     * @param partId The specified Part's ID
     * @return A list of a Part's materials
     */
    @GetMapping("/{partId}/materials")
    public ResponseEntity<?> retrivePartMaterials(@PathVariable String partId) {
        return ResponseEntity.ok((partMaterialService.getPartMaterials(partId)));
    }

    /**
     * Gets the part's materials
     *
     * @param partId The part's id
     * @return List of Part Materials
     */
    @GetMapping("/materials/{partId}")
    public ResponseEntity<?> retrieveAllMaterailByParts(@PathVariable String partId){
        String pid = String.valueOf(partId);
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(partMaterialService.getAllMaterialsOfPart(pid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
