package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends CrudRepository<Product,Integer> {
    List<Product> findByName(String name);
}
