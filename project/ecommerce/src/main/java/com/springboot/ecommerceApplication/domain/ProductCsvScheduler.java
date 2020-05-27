package com.springboot.ecommerceApplication.domain;

import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.services.MailService;
import com.springboot.ecommerceApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductCsvScheduler {
    @Autowired
    MailService mailService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductService productService;



    @Scheduled(fixedRateString = "${scheduler.timeInterval.activateUser}")
    public void sendCreatedProductDetails() {

      //  Iterable<Product> list = productRepo.findAll();
      // List<Product> lists = new ArrayList();

        //ProductService productService = new ProductService();
        Product product = new Product();
        ///  Product list =productRepo.findbyCreatedTime(product.getCreationTime());
          Date currentTime = new Date();
       // for (Product products : list) {

            Product date = productRepo.findByCreationTime();
           Long value = currentTime.getTime() - date.getCreationTime().getTime();

//           // logger.info("this is a logger{}",);
           // System.out.println("###########################" + value);
            //if (value >= 86400000) {
            if (value >= 60000) {
                //String email= product.getSeller().getEmail();
                // product.getCreatedTime();
                String email = "eller.user@gmail.com";
                //System.out.println("########################"+currentTime);
                mailService.sendProductDetails(email);


            }
        }

    }

