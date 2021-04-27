package com.eldho.charter.repo01;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eldho.charter.repo01.entity.BranchEntity;
import com.eldho.charter.repo01.entity.TransactionsEntity;
import com.eldho.charter.repo01.repo.BranchRepository;
import com.eldho.charter.repo01.repo.TransactionsRepository;

@Service
public class Db01Service {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private TransactionsRepository txnRepository;

    private static final Logger logger = LogManager.getLogger(Db01Service.class);

    @Transactional(value = "db01TransactionManager")
    @Cacheable(cacheNames="branchCache", key="#branchId")
    public BranchEntity getBranchById(String branchId) {
        logger.info("Reading from database (not cache)");
        return branchRepository.getOne(branchId);
    }

    @Transactional(value = "db01TransactionManager")
    public List<TransactionsEntity> getTransactionsForCustomer(String customerId) {
    	logger.info("Reading transactions from database for customer {}", customerId);

    	List<String> list = new ArrayList<>();
    	list.add(customerId);
    	List<TransactionsEntity> txns = txnRepository.findAllById(list);
    	return txns;
    }
}
