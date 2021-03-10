package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawmaterials")
public class RawMaterialController {

    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    RawMaterialService rawMaterialService;

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllRawMaterials(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.getAllRawMaterial()) , HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{rid}")
    public ResponseEntity<?> retrieveRawMaterial(@PathVariable String rid){
        String rawMaterialID = String.valueOf(rid);
        RawMaterialRequestDto rawMaterialRequestDto = rawMaterialService.getRawMaterialById(rawMaterialID);
        try{
            if (rawMaterialRequestDto != null) {
                return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialRequestDto), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Raw material was not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/define")
    public ResponseEntity<?> defineRawMaterial(@RequestBody RawMaterialRequestDto rawMaterialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.createNewRawMaterial(rawMaterialRequestDto)) , HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/edit/{rid}")
    public ResponseEntity<?> editRawMaterial(@PathVariable String rid, @RequestBody RawMaterialRequestDto rawMaterialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.updateRawMaterial(rid,rawMaterialRequestDto)) , HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{rid}")
    public ResponseEntity<?> removeRawMaterial(@PathVariable String rid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.deleteRawMaterial(rid)) , HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
