package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.ForgotPasswordToken;
import com.springboot.ecommerce.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface ForgotPasswordTokenRepo extends CrudRepository<ForgotPasswordToken, Integer> {
    ForgotPasswordToken findByToken(String token);
    ForgotPasswordToken findByUser(User user);
}
