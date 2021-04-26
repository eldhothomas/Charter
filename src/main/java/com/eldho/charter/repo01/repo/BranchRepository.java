package com.eldho.charter.repo01.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eldho.charter.repo01.entity.BranchEntity;

public interface BranchRepository extends JpaRepository<BranchEntity, String> {

}