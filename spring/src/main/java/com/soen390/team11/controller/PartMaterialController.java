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

@RestController
@RequestMapping("/parts")
public class PartMaterialController {
    ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    PartMaterialService partMaterialService;

    @Autowired
    PartService partService;

    @GetMapping("/{partId}/materials")
    public ResponseEntity<?> retrivePartMaterials(@PathVariable Long partId){
        return ResponseEntity.ok((partMaterialService.getPartMaterials(partId)));
    }
    @GetMapping("/materials/{partId}")
    public ResponseEntity<?> retrieveAllMaterailByParts(@PathVariable String partId){
        Long pid = Long.valueOf(partId);
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(partMaterialService.getAllMaterialsOfPart(pid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
