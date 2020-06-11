package com.springboot.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    AppUserDetailsService userDetailsService;

    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/getEmails","/products","/index","/csvTest","/export-users","/test","/customerRegister","/activate-user-account","/sellerRegister","/seller/{id}","/activate/{id}","/upload/user","/upload/product","/upload/product-variation","/show/user","/show/product","/show/product-variation","/api/v1/forgotPassword","/login-user").anonymous()
                .antMatchers("/product/","/customer/delete/{id}","/seller/","/customer/","/activate/{id}","/deactivate/{id}","/changeRole/{role}/{id}","/updateAdmin","/activateProduct/{id}","/category/add","/category/{categoryId}","/category/list","/category/update/{categoryId}","/MetadataField/add","/MetadataField/list","/metadataFieldValues/add","/metadataFieldValues/update/{metadataValueId}","/viewProduct/{productId}").hasAnyRole("ADMIN")
                .antMatchers("product/{id}","/order/{id}","/register-account","/activate-account","/logout","/product/","/cart","/order","/reset-password","/login/forgotPassword").hasAnyRole("ADMIN","CUSTOMER","SELLER")
                .antMatchers("/address/view","/customer/viewProfile","/customer/update","/address/{id}","/address/Add","/order/","/view-myProduct/{productId}","/customer/changePassword","/address/Customer{id}","/address/delete/{id}","/get-product-list/{categoryId}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/add-product-variation/{productId}","seller/update/{id}","/seller/address/{id}","/add-product","/get-product-list","/get-product/{productId}","/update-product/{productId}","/delete/{productId}","/changePassword","/update-productVariation/{productVariationId}","/seller/viewProfile","/seller/update","/get-productVariation/{productVariationId}","/get-productVariation-list/{productId}").hasAnyRole("SELLER","ADMIN")
                .antMatchers("/address/Seller/{id}","/address/Customer{id}","/address/view").hasAnyRole("SELLER", "CUSTOMER")
                .antMatchers("/export-product-details").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();
    }
}