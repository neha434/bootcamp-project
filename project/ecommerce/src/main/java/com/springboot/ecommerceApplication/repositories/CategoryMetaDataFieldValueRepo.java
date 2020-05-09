package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.CategoryMetaDataField;
import com.springboot.ecommerceApplication.domain.product.CategoryMetaDataFieldValues;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetaDataFieldValueRepo extends CrudRepository<CategoryMetaDataFieldValues,Integer> {
   // CategoryMetaDataFieldValueRepo findByName(String name);
}
