package com.springboot.ecommerceApplication.dto;

import javax.persistence.criteria.CriteriaBuilder;

public class SuccessDto {
    private Integer id;
    private  String Success;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }
}
