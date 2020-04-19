package com.springboot.ecommerceApplication.dto;

public class PagingAndSortingDto {

    private Integer max;
    private Integer offset;
    private String sortField;
    private String order;

    public Integer getMax(int i) {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getOffset(int i) {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getMax() {
        return 0;
    }

    public int getOffset() {
        return 0;
    }
}
