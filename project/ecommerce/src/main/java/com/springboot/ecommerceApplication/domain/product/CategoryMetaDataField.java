package com.springboot.ecommerceApplication.domain.product;

import javax.persistence.*;
import java.util.Set;
@Entity
public class CategoryMetaDataField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "categoryMetaDataField", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CategoryMetaDataFieldValues> categoryMetaDataFieldValues;
    public Set<CategoryMetaDataFieldValues> getCategoryMetaDataFieldValues() {
        return categoryMetaDataFieldValues;
    }
    public void setCategoryMetadataFieldValues(Set<CategoryMetaDataFieldValues> categoryMetadataFieldValues) {
        this.categoryMetaDataFieldValues = categoryMetaDataFieldValues;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
