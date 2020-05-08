package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category,Integer> {
    Category findByName(String name);
}
