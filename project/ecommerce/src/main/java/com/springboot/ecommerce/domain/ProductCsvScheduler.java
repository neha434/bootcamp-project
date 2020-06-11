package com.springboot.ecommerce.domain;

import com.springboot.ecommerce.repositories.ProductRepo;
import com.springboot.ecommerce.repositories.SellerRepo;
import com.springboot.ecommerce.services.MailService;
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
    @Autowired
    SellerRepo sellerRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Scheduled(fixedRateString = "${scheduler.timeInterval.activateUser}")
     public void sendCreatedProductDetails() throws  Exception{

        List<Integer> sellerIds = productRepo.sendProductDetailsCreated24Hours();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = simpleDateFormat.format(date);

        for(Integer value :sellerIds)
        {
            Integer id= value;
           String email=  sellerRepo.findById(value).get().getEmail();
            logger.info("################################################################scheduler is running");
            mailService.sendProductDetails(email,id,currentTime);

        }


    }

}
