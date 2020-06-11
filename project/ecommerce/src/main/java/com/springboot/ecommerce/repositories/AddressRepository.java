package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.domain.user.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
