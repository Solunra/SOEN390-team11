package com.soen390.team11.controller;

import com.soen390.team11.service.PartMaterialService;
import com.soen390.team11.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/parts")
public class PartMaterialController {

    @Autowired
    PartMaterialService partMaterialService;

    @Autowired
    PartService partService;

    @GetMapping("/{partId}/materials")
    public ResponseEntity<?> retrivePartMaterials(@PathVariable Long partId){
        return ResponseEntity.ok((partMaterialService.getPartMaterials(partId)));
    }
}
