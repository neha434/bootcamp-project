package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.order.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart,Integer> {
}
