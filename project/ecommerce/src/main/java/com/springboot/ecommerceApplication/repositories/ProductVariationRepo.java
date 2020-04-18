package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import org.springframework.data.repository.CrudRepository;

public interface ProductVariationRepo extends CrudRepository<ProductVariation,Integer> {
}
