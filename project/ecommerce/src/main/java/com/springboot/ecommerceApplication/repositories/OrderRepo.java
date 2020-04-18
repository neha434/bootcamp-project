package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order,Integer> {
}
