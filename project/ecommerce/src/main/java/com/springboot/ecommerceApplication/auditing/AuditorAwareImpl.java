package com.springboot.ecommerceApplication.auditing;

import com.springboot.ecommerceApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Autowired
    UserService userService;

    @Override
    public Optional<String> getCurrentAuditor() {
       return null;
    }
}
