package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.user.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepo extends CrudRepository<Customer,Integer> {
    Customer findByEmail(String email);
    List<Customer> findAll(Pageable pageable);


}