package com.eldho.charter.service;

import java.math.BigDecimal;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PointsCalculatorService {

	private static final Logger logger = LogManager.getLogger(PointsCalculatorService.class);

	/**
	 * A customer receives 2 points for every dollar spent over $100 in each
	 * transaction, plus 1 point for every dollar spent over $50 in each transaction
	 * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). Given a record of every
	 * transaction during a three month period, calculate the reward points earned
	 * for each customer per month and total.
	 * 
	 * @param purchaseAmount
	 * @return
	 */
	public Integer calculatePoints(BigDecimal purchaseAmount) {

		//With some work, these can be configured in the database as global properties instead of hard-coding
		BigDecimal limit1 = new BigDecimal(100);
		BigDecimal limit2 = new BigDecimal(50);

		Integer points = 0;
		if (purchaseAmount.compareTo(limit1) > 0) {
			logger.log(Level.INFO, "Greater than {}", limit1);
			BigDecimal overLimit1 = purchaseAmount.subtract(limit1);
			// Two points for every dollar
			points = overLimit1.intValue() * 2;
			// One point for every dollar
			points = points + limit1.subtract(limit2).intValue();
		} else if (purchaseAmount.compareTo(limit2) > 0) {
			logger.log(Level.INFO, "Greater than {}", limit2);
			BigDecimal overLimit2 = purchaseAmount.subtract(limit2);
			// One point for every dollar
			points = overLimit2.intValue();
		}
		logger.log(Level.INFO, "Total points {}", points);

		return points;
	}
}
