package com.eldho.charter.repo01;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eldho.charter.repo01.entity.TransactionsEntity;
import com.eldho.charter.repo01.repo.TransactionsRepository;

@Service
public class Db01Service {

    @Autowired
    private TransactionsRepository txnRepository;

    private static final Logger logger = LogManager.getLogger(Db01Service.class);

    @Transactional(value = "db01TransactionManager")
    public List<TransactionsEntity> getTransactionsForCustomer(String customerId) {
    	logger.info("Reading transactions from database for customer {}", customerId);
    	List<TransactionsEntity> txns = txnRepository.findTransactionsForCustomer(customerId);
    	return txns;
    }
}
