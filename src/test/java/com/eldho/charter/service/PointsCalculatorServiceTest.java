package com.eldho.charter.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Eldho Thomas
 * 
 *         This has JUnit methods for testing PointsCalculatorService. Only one
 *         sample test is added. For most other methods, I will need to add
 *         Mockito objects to mock the interfaces (such as databases).
 * 
 *         Note: I haven't been able to get this test to execute. For some
 *         reason, my Gradle test task doesn't execute the test
 *
 */
class PointsCalculatorServiceTest {

	private static final Logger logger = LogManager.getLogger(PointsCalculatorServiceTest.class);

	@Autowired
	private PointsCalculatorService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test the points for purchase amount")
	void testCalculatePointsForTransaction() {
		logger.log(Level.INFO, "Unit testing for purchase amount 2000");
		Integer points = service.calculatePointsForTransaction(new BigDecimal("200.00"));
		assertEquals(250, points, "Points for $2000 should equal 250");
	}

}
