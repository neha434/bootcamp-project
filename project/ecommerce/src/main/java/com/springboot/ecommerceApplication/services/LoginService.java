package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.exception.UserAccountLocked;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepo;

    public void login(String email, String password, String clientId, String clientSecret) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = null;
        User user = userRepo.findByEmail(email);

        try {

            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            checkUserLock(email);
            if (user.isActive()) {
                throw new AuthenticationCredentialsNotFoundException("Bad Credentials Entered");
            } else {
                throw new UserAccountLocked("Your Account Has Been Locked");
            }

        }
        User authUser = (User) authentication.getPrincipal();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkUserLock(String email) {
        User user = userRepo.findByEmail(email);

        if (user.getCount() < 3) {
            Integer counter = user.getCount();
            user.setCount(++counter);
            userRepo.save(user);
            //throw new AuthenticationCredentialsNotFoundException("Bad credential entered");
        } else {
            user.setActive(false);
            userRepo.save(user);
            //throw new InvalidDetails("Your Account has been locked");
        }
    }
}