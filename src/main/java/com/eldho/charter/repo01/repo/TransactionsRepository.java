package com.eldho.charter.repo01.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eldho.charter.repo01.entity.TransactionsEntity;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, String> {

}
