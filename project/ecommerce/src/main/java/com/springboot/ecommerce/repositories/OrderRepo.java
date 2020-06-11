package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order,Integer> {
}
