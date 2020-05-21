package com.springboot.ecommerceApplication.auditing;

import com.springboot.ecommerceApplication.repositories.UserRepo;
import com.springboot.ecommerceApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
   @Autowired
    UserRepo userRepo;

//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.empty();
//    }


   @Override
    public Optional<String> getCurrentAuditor() {
    Optional<String> user= Optional.empty();
     String principal = getCurrentlyLoggedInUser();
    user = Optional.of(principal);
    return user;
    }
    private String getCurrentlyLoggedInUser(WebRequest webRequest){
        Authentication auth = (Authentication) webRequest.getUserPrincipal();
        return String.valueOf(userRepo.findByEmail(auth.getName()));

//


}