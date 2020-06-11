package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.product.CategoryMetaDataFieldValues;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetaDataFieldValueRepo extends CrudRepository<CategoryMetaDataFieldValues,Integer> {
}
