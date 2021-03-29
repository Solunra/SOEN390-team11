package com.soen390.team11.controller;

import com.soen390.team11.dto.PartMaterialResponse;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartMaterial;
import com.soen390.team11.repository.MaterialRepository;
import com.soen390.team11.repository.PartMaterialRepository;
import com.soen390.team11.repository.PartRepository;
import com.soen390.team11.service.PartMaterialService;
import com.soen390.team11.service.PartService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PartMaterialControllerTest {

    PartMaterialController partMaterialController;
    @Mock
    PartMaterialService partMaterialService;
    @Mock
    PartService partService;


    @BeforeEach
    public void setup() {
        openMocks(this);
        partMaterialController = new PartMaterialController(partMaterialService, partService);
    }

    @Test
    void retrieveAllMaterialsOfPartInInventory_Success() {
        Map<String, Part> partMap = new HashMap<>();
        partMap.put("saddle", new Part("saddle","saddle"));
        partMap.put("wheel", new Part("wheel","wheel"));

        Map<String, Material> materialMap = new HashMap<>();
        materialMap.put("dummy1", new Material("mat-100", "dummy1"));
        materialMap.put("dummy2", new Material("mat-101", "dummy2"));

        Map<String, PartMaterial> partMaterialMap = new HashMap<>();
        partMaterialMap.put("saddle_dummy1", new PartMaterial(partMap.get("saddle").getPartid(), materialMap.get("dummy1").getMaterialid(), 7));
        partMaterialMap.put("saddle_dummy2", new PartMaterial(partMap.get("saddle").getPartid(), materialMap.get("dummy2").getMaterialid(), 13));

        Map<String, Integer> expectedPartMaterialsMap = new HashMap<>();
        expectedPartMaterialsMap.put(materialMap.get("dummy1").getMaterialid(), 7);
        expectedPartMaterialsMap.put(materialMap.get("dummy2").getMaterialid(), 13);

        when(partMaterialService.getPartMaterials(partMap.get("saddle").getPartid())).thenReturn(expectedPartMaterialsMap);
        ResponseEntity<?> saddleRetrieveResponse = partMaterialController.retrivePartMaterials(partMap.get("saddle").getPartid());

        assertEquals(200, saddleRetrieveResponse.getStatusCodeValue());
        assertEquals(expectedPartMaterialsMap, saddleRetrieveResponse.getBody());
    }

}