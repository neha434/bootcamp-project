package com.springboot.ecommerceApplication.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionUserService {
  //private static final Logger logger = (Logger) LoggerFactory.getLogger(TransactionUserService.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void parentMethod() {
        System.out.println("*******************First transaction");
        System.out.println("*********************Second transaction");
        System.out.println("************************Third transaction");
      //  logger.info("###########################abxe", a);
      //  audit_table.setMessages("method 1");
//
        try {
            int a = 2 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
       // int g= 5/0;
        childMethod();
        System.out.println("***********************Fourth transaction");
        System.out.println("**************************Fifth transaction");
        System.out.println("****************************Sixth transaction");

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void childMethod(){
        System.out.println("*************************************** child zero transaction");
//        try {
//            int a = 2 / 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        int a= 2/0;


    }
}
