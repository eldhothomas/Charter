package com.eldho.charter.repo01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eldho.charter.repo01.entity.BranchEntity;
import com.eldho.charter.repo01.repo.BranchRepository;

@Service
public class Db01Service {

    @Autowired
    private BranchRepository branchRepository;

    private static final Logger logger = LogManager.getLogger(Db01Service.class);

    @Transactional(value = "db01TransactionManager")
    @Cacheable(cacheNames="branchCache", key="#branchId")
    public BranchEntity getBranchById(String branchId) {
        logger.info("Reading from database (not cache)");
        return branchRepository.getOne(branchId);
    }

}
