package com.soen390.team11.controller;

import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.PartRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PartInventoryControllerTest {

    @Autowired
    PartInventoryController partInventoryController;
    @Autowired
    PartRepository partRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    PartInventoryRepository partInventoryRepository;

    Map<String, PartInventory> partInventoryMap = new HashMap<>();
    Map<String, Part> partMap = new HashMap<>();
    Map<String, Material> materialMap = new HashMap<>();

    @BeforeAll
    public void setup() {
        partMap.put("saddle", partRepository.save(new Part("saddle")));
        partMap.put("wheel", partRepository.save(new Part("wheel")));
        materialMap.put("dummy1", materialRepository.save(new Material(100L, "dummy1")));
        materialMap.put("dummy2", materialRepository.save(new Material(101L, "dummy2")));
        PartInventory inventory1 = new PartInventory(partMap.get("saddle").getPartid(), materialMap.get("dummy1").getMaterialid(), 7);
        PartInventory inventory2 = new PartInventory(partMap.get("saddle").getPartid(), materialMap.get("dummy2").getMaterialid(), 13);
        partInventoryMap.put("saddle_dummy1", partInventoryRepository.save(inventory1));
        partInventoryMap.put("saddle_dummy2", partInventoryRepository.save(inventory2));
    }

    @Test
    @Order(2)
    void retrieveMaterialsOfPartInInventory_Success() {
        ResponseEntity<?> saddleRetrieveResponse = partInventoryController.retrievePartMaterials(partMap.get("saddle").getPartid());
        assertNotNull(saddleRetrieveResponse.getBody());
    }

    @AfterAll
    public void cleanup() {

        for (PartInventory partInventory : partInventoryMap.values()) {
            partInventoryRepository.delete(partInventory);
        }
        for (Material material : materialMap.values()) {
            materialRepository.delete(material);
        }
        for (Part part : partMap.values()) {
            partRepository.delete(part);
        }

    }
}