package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for Raw Materials
 */
@Controller
@RequestMapping("/rawmaterials")
public class RawMaterialController {

    ObjectMapper objectMapper= new ObjectMapper();

    RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    /**
     * Gets all raw materials
     *
     * @return List of all raw materials
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllRawMaterials(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(rawMaterialService.getAllRawMaterial()) , HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Gets a specified raw material
     *
     * @param rid The raw material's ID
     * @return The raw material's information
     */
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

    /**
     * Create a new raw material
     *
     * @param rawMaterialRequestDto The new raw material information
     * @return The new raw material
     */
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

    /**
     * Edits an existing raw material
     *
     * @param rid The ID of the new raw material
     * @param rawMaterialRequestDto The information for the new raw material
     * @return The ID
     */
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

    /**
     * Deletes a raw material
     *
     * @param rid The raw material's ID
     * @return A message of its status
     */
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
