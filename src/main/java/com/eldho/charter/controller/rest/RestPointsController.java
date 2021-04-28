package com.eldho.charter.controller.rest;

import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eldho.charter.service.PointsCalculatorService;

/**
 * 
 * @author Eldho Thomas
 * 
 * REST Controller for the Points API
 *
 */


@RestController
@RequestMapping("/rest/points")
public class RestPointsController {
    private static final Logger logger = LogManager.getLogger(RestPointsController.class);

    @Autowired
    private PointsCalculatorService pointsCalculatorService;

    @RequestMapping("/calculatePointsForPurchase")
    public ResponseEntity<Integer> calculatePointsForPurchase(@RequestParam(value = "purchaseAmount", defaultValue = "200.50") BigDecimal purchaseAmount) {
    	logger.log(Level.INFO, "Received request to calculate points for purchase amount: {}", purchaseAmount);
    	Integer points = pointsCalculatorService.calculatePointsForTransaction(purchaseAmount);
    	logger.log(Level.INFO, "Calculated points: {}", points);
        return new ResponseEntity<Integer>(points, HttpStatus.OK);
    }

    @RequestMapping("/calculatePointsForCustomer")
    public ResponseEntity<Integer> calculatePointsForCustomer(@RequestParam(value = "customerId", defaultValue = "CCCC") String customerId) {
    	logger.log(Level.INFO, "Received request to calculate points for customer {}", customerId);
    	Integer points = pointsCalculatorService.calculatePointsForCustomer(customerId);
    	logger.log(Level.INFO, "Calculated points: {}", points);
        return new ResponseEntity<Integer>(points, HttpStatus.OK);
    }
    
}
