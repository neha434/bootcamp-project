package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.product.ProductVariation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductVariationRepo extends CrudRepository<ProductVariation,Integer> {
    List<ProductVariation> findAll(Pageable paging);
}
