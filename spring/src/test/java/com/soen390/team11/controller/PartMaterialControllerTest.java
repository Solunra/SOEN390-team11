package com.soen390.team11.controller;

import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartMaterial;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.PartMaterialRepository;
import com.soen390.team11.repository.PartRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PartMaterialControllerTest {

    @Autowired
    PartMaterialController partMaterialController;
    @Autowired
    PartRepository partRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    PartMaterialRepository partMaterialRepository;

    Map<String, PartMaterial> partMaterialMap = new HashMap<>();
    Map<String, Part> partMap = new HashMap<>();
    Map<String, Material> materialMap = new HashMap<>();

    @BeforeAll
    public void setup() {
        partMap.put("saddle", partRepository.save(new Part("saddle","saddle")));
        partMap.put("wheel", partRepository.save(new Part("wheel","wheel")));
        materialMap.put("dummy1", materialRepository.save(new Material("mat-100", "dummy1")));
        materialMap.put("dummy2", materialRepository.save(new Material("mat-101", "dummy2")));
        PartMaterial partMaterial1 = new PartMaterial(partMap.get("saddle").getPartid(), materialMap.get("dummy1").getMaterialid(), 7);
        PartMaterial partMaterial2 = new PartMaterial(partMap.get("saddle").getPartid(), materialMap.get("dummy2").getMaterialid(), 13);
        partMaterialMap.put("saddle_dummy1", partMaterialRepository.save(partMaterial1));
        partMaterialMap.put("saddle_dummy2", partMaterialRepository.save(partMaterial2));
    }

    @Test
    void retrieveAllMaterialsOfPartInInventory_Success() {
        ResponseEntity<?> saddleRetrieveResponse = partMaterialController.retrivePartMaterials(partMap.get("saddle").getPartid());

        Map<String, Integer> expectedPartMaterialsMap = new HashMap<>();
        expectedPartMaterialsMap.put(materialMap.get("dummy1").getMaterialid(), 7);
        expectedPartMaterialsMap.put(materialMap.get("dummy2").getMaterialid(), 13);

        assertEquals(200, saddleRetrieveResponse.getStatusCodeValue());
        assertEquals(expectedPartMaterialsMap, saddleRetrieveResponse.getBody());
    }

    @AfterAll
    public void cleanup() {

        for (PartMaterial partMaterial : partMaterialMap.values()) {
            partMaterialRepository.delete(partMaterial);
        }
        for (Material material : materialMap.values()) {
            materialRepository.delete(material);
        }
        for (Part part : partMap.values()) {
            partRepository.delete(part);
        }

    }
}