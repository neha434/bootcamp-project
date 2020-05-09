package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.CategoryMetaDataField;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetadataFieldRepo extends CrudRepository<CategoryMetaDataField,Integer> {
    CategoryMetaDataField findByName(String name);
}
