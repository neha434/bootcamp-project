package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductVariationRepo extends CrudRepository<ProductVariation,Integer> {
    List<ProductVariation> findAll(Pageable paging);
}
