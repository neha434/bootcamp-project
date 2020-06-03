package com.springboot.ecommerceApplication;

import com.springboot.ecommerceApplication.auditing.AuditorAwareImpl;
import com.springboot.ecommerceApplication.domain.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EcommerceApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
				SpringApplication.run(EcommerceApplication.class, args);


	}


	}

