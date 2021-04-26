package com.eldho.charter.controller.rest;

import com.eldho.charter.repo01.entity.BranchEntity;

public class RestDbBranchVo {

    private final String id;
    private final String name;

    public RestDbBranchVo(BranchEntity branchEntity) {
        this.id = branchEntity.getId();
        this.name = branchEntity.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
