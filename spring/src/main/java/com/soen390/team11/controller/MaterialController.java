package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.entity.Material;
import com.soen390.team11.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {
    ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    MaterialService materialService;
    @PostMapping("/create")
    public ResponseEntity<?> createMaterial(@RequestBody MaterialRequestDto materialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.createMaterial(materialRequestDto)), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllMaterial(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterial()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveMaterial(@PathVariable Long id){
        Material material = materialService.getMaterialById(id);
        try {
            if (material != null) {
                return new ResponseEntity<>(objectMapper.writeValueAsString(material), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("material of the id not exist ", HttpStatus.NOT_FOUND);
            }
        }
        catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
     }

     @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable Long id, @RequestBody MaterialRequestDto materialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.updateMaterial(id,materialRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("material of the id not exist", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.deleteMaterial(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>("material of the id not exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{materialid}")
    public ResponseEntity<?> retrieveAllRawMaterialsInMaterial(@PathVariable Long materialid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterialRawMaterial(materialid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/rawmaterial")
    public ResponseEntity<?> retrieveAllMaterials(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterialsIninventory()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

}