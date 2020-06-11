package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.product.Product;
import com.springboot.ecommerce.domain.user.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Integer> {
    Product findByName(String name);
    Product findBySeller(Seller id);

    @Query(value = "select * from PRODUCT where created_data BETWEEN :startTime AND :endTime  AND seller_user_id=:id", nativeQuery = true)
    List<Product> sendProductDetailsCreated24Hours(@Param("id") Integer id,@Param("startTime")
            LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query(value = "select distinct (seller_user_id) from PRODUCT where created_data >= NOW() - INTERVAL 1 DAY", nativeQuery = true)
    List<Integer> sendProductDetailsCreated24Hours();

}
