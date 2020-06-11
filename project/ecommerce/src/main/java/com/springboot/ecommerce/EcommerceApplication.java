package com.springboot.ecommerce;

import com.springboot.ecommerce.auditing.AuditorAwareImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EcommerceApplication implements CommandLineRunner {
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
				SpringApplication.run(EcommerceApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("TestExchange","testRouting","hi from ecommerce");

	}
}

