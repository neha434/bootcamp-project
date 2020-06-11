package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.product.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category,Integer> {
    Category findByName(String name);
}