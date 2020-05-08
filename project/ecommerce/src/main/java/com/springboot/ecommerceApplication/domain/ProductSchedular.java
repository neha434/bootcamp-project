package com.springboot.ecommerceApplication.domain;


import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import com.springboot.ecommerceApplication.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ProductSchedular {
    @Autowired
    ProductVariationRepo productVariationRepo;
    @Autowired
    MailService mailService;

    @Scheduled(cron = "0 0 7,19 * * *")
    public void sendProductDetails(){
       Iterable<ProductVariation> savedProduct = productVariationRepo.findAll();
       Iterator<ProductVariation> itr= savedProduct.iterator();
       while (itr.hasNext()){
           if(itr.next().getQuantityAvailable()==0)
               itr.next().setActive(false);
         //  mailService.sendProductDetailEmail(itr.next().getProduct().getSeller().getEmail(), itr.next().getProduct().getName());
        }

    }
}
