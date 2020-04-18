package com.springboot.ecommerceApplication.repositories;

import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
   //Customer findByEmail(String email);

}
