package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepo extends CrudRepository<Seller,Integer> {
    Seller findByEmail(String string);
    List<Seller> findAll(Pageable pageable);

}
