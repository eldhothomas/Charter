package com.eldho.charter.controller.rest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eldho.charter.repo01.Db01Service;
import com.eldho.charter.repo01.entity.BranchEntity;

@RestController
@RequestMapping("/rest/db")
public class RestDbController {

    @Autowired
    private Db01Service db01Service;
 
    private static final Logger logger = LogManager.getLogger(RestDbController.class);

    @RequestMapping("/getBranchName")
    public ResponseEntity<RestDbBranchVo> getBranchName(@RequestParam(value = "branchId", defaultValue = "B001") String branchId) {
    	logger.log(Level.INFO, "Received request for Branch. BranchId: {}", branchId);
    	BranchEntity branchEntity = db01Service.getBranchById(branchId);
    	logger.log(Level.INFO, "Retrieved branch. Name: {}", branchEntity.getName());
        return new ResponseEntity<RestDbBranchVo>(new RestDbBranchVo(branchEntity), HttpStatus.OK);
    }

}
