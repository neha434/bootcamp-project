package com.springboot.ecommerce.security;

import com.springboot.ecommerce.domain.Role;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDao {
    @Autowired
    UserRepo userRepository;

    public AppUser loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        List<GrantAuthorityImpl> grantAuthorityList = new ArrayList<>();
        List<Role> roleList = user.getRoleList();

        roleList.forEach(role ->
        {grantAuthorityList.add(new GrantAuthorityImpl(role.getAuthority()));
        }
        );
        if (username != null) {
            return  new AppUser(user.getEmail(),
                    user.getPassword(),
                    grantAuthorityList);
        }
        else {
            throw new RuntimeException();
        }

    }

}