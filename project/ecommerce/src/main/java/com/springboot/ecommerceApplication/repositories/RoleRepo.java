package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Integer>{
    Role findByAuthority(String authority);
}
