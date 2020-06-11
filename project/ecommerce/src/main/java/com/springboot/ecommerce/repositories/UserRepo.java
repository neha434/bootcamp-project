package com.springboot.ecommerce.repositories;


import com.springboot.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {



    User findByEmail(String email);

    Optional<User> findById(Integer id);

    List<User> findByIsActive(boolean value);


    @Query(
            value = "Update User set isActive = true WHERE deactivatedTime >(NOW() - INTERVAL 24 HOUR)",
            nativeQuery = true)
    void ActivateUsers();


}