package com.springboot.ecommerceApplication.domain;

import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ProductCsvScheduler {
    @Autowired
    MailService mailService;
    @Autowired
    ProductRepo productRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Scheduled(fixedRateString = "${scheduler.timeInterval.activateUser}")
     public void sendCreatedProductDetails(){

        List<Product> products = productRepo.sendProductDetailsCreated24Hours();

        for(Product product :products)
        {
          Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = simpleDateFormat.format(date);
            String email = product.getSeller().getEmail();
            Integer id = product.getSeller().getId();
            mailService.sendProductDetails(email,id,currentTime);
            logger.info("################################################################scheduler is running");
        }



    }

}
