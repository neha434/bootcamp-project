package com.springboot.ecommerce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CsvDetails {
    @Id
    private Integer id;
    private String csvName;
    private Integer sellerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCsvName() {
        return csvName;
    }

    public void setCsvName(String csvName) {
        this.csvName = csvName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
}

