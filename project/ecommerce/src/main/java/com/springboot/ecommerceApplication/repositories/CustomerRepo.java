package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.user.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CustomerRepo extends CrudRepository<Customer,Integer> {
    Customer findByEmail(String email);
    List<Customer> findAll(Pageable pageable);


}