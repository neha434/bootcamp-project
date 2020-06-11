package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.order.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart,Integer> {
}
