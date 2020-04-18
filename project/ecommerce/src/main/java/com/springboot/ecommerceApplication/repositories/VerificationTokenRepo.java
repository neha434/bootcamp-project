package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepo extends CrudRepository<VerificationToken,Integer>
{
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
