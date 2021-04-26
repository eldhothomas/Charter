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

@RestController
@RequestMapping("/rest/points")
public class RestPointsController {
    private static final Logger logger = LogManager.getLogger(RestPointsController.class);

    @Autowired
    private PointsCalculatorService pointsCalculatorService;

    @RequestMapping("/calculatePointsForPurchase")
    public ResponseEntity<Integer> getBranchName(@RequestParam(value = "purchaseAmount", defaultValue = "200.50") BigDecimal purchaseAmount) {
    	logger.log(Level.INFO, "Received request to calculate purchase amount. Purchase amount: {}", purchaseAmount);
    	Integer points = pointsCalculatorService.calculatePoints(purchaseAmount);
    	logger.log(Level.INFO, "Calculated points: {}", points);
        return new ResponseEntity<Integer>(points, HttpStatus.OK);
    }
}
