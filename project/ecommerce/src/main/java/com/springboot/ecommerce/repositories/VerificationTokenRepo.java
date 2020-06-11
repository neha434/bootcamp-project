package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.VerificationToken;
import com.springboot.ecommerce.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepo extends CrudRepository<VerificationToken,Integer>
{
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
