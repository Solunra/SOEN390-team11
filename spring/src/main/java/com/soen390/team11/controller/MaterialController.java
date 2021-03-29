package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.entity.Material;
import com.soen390.team11.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for the materials
 */
@RestController
@RequestMapping("/material")
public class MaterialController {
    ObjectMapper objectMapper= new ObjectMapper();

    MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    /**
     * Creates a new material
     *
     * @param materialRequestDto The expected body of the request
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createMaterial(@RequestBody MaterialRequestDto materialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.createMaterial(materialRequestDto)), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Method to retrieve all materials
     *
     * @return A list of all materials registered in the ERP solution
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllMaterial(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterial()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to retrieve a specific material
     *
     * @param id The id of the material
     * @return The material's information
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveMaterial(@PathVariable String id){
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

    /**
     * Updates a specific material accessed via its ID
     *
     * @param id The material's ID
     * @param materialRequestDto The expected request body
     * @return The
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable String id, @RequestBody MaterialRequestDto materialRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.updateMaterial(id,materialRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("material of the id not exist", HttpStatus.CONFLICT);
        }
    }

    /**
     * Deletes a material
     *
     * @param id The material's ID
     * @return The ID of the material
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable String id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.deleteMaterial(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>("material of the id not exist", HttpStatus.CONFLICT);
        }
    }

    /**
     * Obtains a specific material based on the provided ID
     *
     * @param materialid The material's ID
     * @return The raw materials that consists a material
     */
    @GetMapping("/{materialid}")
    public ResponseEntity<?> retrieveAllRawMaterialsInMaterial(@PathVariable String materialid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterialRawMaterial(materialid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Gets all materials
     *
     * @return List of all materials
     */
    @GetMapping("/rawmaterial")
    public ResponseEntity<?> retrieveAllMaterials(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materialService.getAllMaterialsIninventory()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

}