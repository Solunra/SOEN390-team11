package com.soen390.team11.controller;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.entity.Log;
import com.soen390.team11.service.CustomerPurchaseService;
import com.soen390.team11.service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DataControllerTest {
    @InjectMocks
    DataController dataController;

    @Mock
    DataService dataService;

    @BeforeEach
    public void setup()
    {
        openMocks(this);
    }
    @Test
    void getIncomeReport() {
        when(dataService.getIncomeReport()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = dataController.getIncomeReport();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getExpenseReport() {
        when(dataService.getExpenseReport()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = dataController.getExpenseReport();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getTopProduct() {
        when(dataService.getTopProduct()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = dataController.getTopProduct();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getSummary() {
        when(dataService.getSummary()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = dataController.getSummary();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}