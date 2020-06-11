package com.springboot.ecommerce;

import com.springboot.ecommerce.repositories.SellerRepo;
import com.springboot.ecommerce.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceApplicationTests {


@Autowired
UserRepo userRepository;
	@Autowired
	SellerRepo sellerRepo;
	@Test
	void contextLoads() {
	}

	public void findByEmail(){
		System.out.println(userRepository.findByEmail("neha.rai8209@gmail.com").getFirstName());
	}



}
