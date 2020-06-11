package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.product.CategoryMetaDataField;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetadataFieldRepo extends CrudRepository<CategoryMetaDataField,Integer> {
    CategoryMetaDataField findByName(String name);
}
