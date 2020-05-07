package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@Service
public class ProductVariationService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductVariationRepo productVariationRepo;
    @Autowired
    SellerRepo sellerRepository;

    @Autowired
    MessageSource messageSource;

    //.........to add product variation by SELLER...........
    public ResponseEntity<String> addProductVariation(String username, Integer productId, Integer price,
                                                      Integer quantity) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepo.findById(productId).isPresent()) {
            throw new InvalidDetails("No such product with the given product Id exist");
        }

        Product product = productRepo.findById(productId).get();
        ResponseEntity<String> responseEntity;
        if (price == null)
            throw new InvalidDetails("Price should be 0 or more ");

        if (quantity == null)
            throw new InvalidDetails("Quantity should be 0 or more ");
        if(!product.isActive())
        {
             throw new  InvalidDetails("Product is not active");
        }
        if(!product.isDeleted())
        {
            throw new InvalidDetails("Product is deleted");
        }
        ProductVariation productVariation = new ProductVariation(price, quantity, product);
        productVariation.setActive(true);
        productVariationRepo.save(productVariation);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-productVariation-added", null, LocaleContextHolder.getLocale()));

        return responseEntity;
    }
}

