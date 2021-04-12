package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controller to handle all the data request
 * can be the request for summary , income ,expense and top product
 */
@RestController
@RequestMapping("/data")
public class DataController {
    ObjectMapper objectMapper = new ObjectMapper();
    DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * get income report to display in table or pie chart
     * @return
     */
    @GetMapping("/income")
    public ResponseEntity<?> getIncomeReport(){
        return new ResponseEntity<>(dataService.getIncomeReport(), HttpStatus.OK);

    }

    /**
     * get expense to display in talbe or pie chart
     * @return
     */
    @GetMapping("/expense")
    public ResponseEntity<?> getExpenseReport(){
        return new ResponseEntity<>(dataService.getExpenseReport(), HttpStatus.OK);

    }

    /**
     * get top product to display in table
     * @return
     */
    @GetMapping("/topProduct")
    public ResponseEntity<?> getTopProduct(){
        return new ResponseEntity<>(dataService.getTopProduct(), HttpStatus.OK);

    }

    /**
     * get all summary to display in table
     * @return
     */
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(){
        return new ResponseEntity<>(dataService.getSummary(), HttpStatus.OK);

    }
}
