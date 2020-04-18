package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.ForgotPasswordToken;
import com.springboot.ecommerceApplication.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface ForgotPasswordTokenRepo extends CrudRepository<ForgotPasswordToken, Integer> {
    ForgotPasswordToken findByToken(String token);
    ForgotPasswordToken findByUser(User user);
}
