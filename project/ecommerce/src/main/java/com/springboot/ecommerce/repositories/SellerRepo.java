package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.user.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepo extends CrudRepository<Seller,Integer> {
    Seller findByEmail(String string);
    List<Seller> findAll(Pageable pageable);

}
