package com.eldho.charter.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eldho.charter.repo01.Db01Service;
import com.eldho.charter.repo01.entity.TransactionsEntity;

@Service
public class PointsCalculatorService {

	private static final Logger logger = LogManager.getLogger(PointsCalculatorService.class);

	@Autowired
    private Db01Service db01Service;

	/**
	 * This method will scan through all the purchase transactions for the customer and add them up to get the total 
	 * points. In real world, this would be more elaborate - we may want to add a range of dates etc.  
	 * 
	 * @param customerId
	 * @return
	 */
	public Integer calculatePointsForCustomer(String customerId) {
		Integer totalPoints = 0;
		
		List<TransactionsEntity> txns = db01Service.getTransactionsForCustomer(customerId);
		Iterator<TransactionsEntity> iter = txns.iterator();
		while (iter.hasNext()) {
			TransactionsEntity txnEntity = iter.next();
			logger.log(Level.INFO, "Transaction - Customer: {}, Txn Amount: {}", txnEntity.getCustomerId(), txnEntity.getPurchaseAmount());
			Integer txnPoints = calculatePointsForTransaction(txnEntity.getPurchaseAmount());
			totalPoints = totalPoints + txnPoints;
			logger.log(Level.INFO, "Points for this transaction: {}; Total points now: {}", txnPoints, totalPoints);
		}

		return totalPoints;
	}

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
	public Integer calculatePointsForTransaction(BigDecimal purchaseAmount) {

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
