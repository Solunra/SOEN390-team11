package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rawmaterials")
public class RawMaterialController {

    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    RawMaterialService rawMaterialService;

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllRawMaterials(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.getAllRawMaterial()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/define")
    public ResponseEntity<?> defineRawMaterial(@RequestBody RawMaterialRequestDto rawMaterialRequestDto){
        try {
            return new ResponseEntity<>(rawMaterialService.createNewRawMaterial(rawMaterialRequestDto), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
