package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Integer> {
    Product findByName(String name);

    @Query(value = "select * from PRODUCT where created_data >= :date - INTERVAL 1 DAY AND seller_user_id=:id", nativeQuery = true)
    List<Product> sendProductDetailsCreated24Hours(@Param("id") Integer id,@Param("date") String date);

    @Query(value = "select * from PRODUCT where created_data >= NOW() - INTERVAL 1 DAY", nativeQuery = true)
    List<Product> sendProductDetailsCreated24Hours();

}
