package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Integer>{
    Role findByAuthority(String authority);
}
