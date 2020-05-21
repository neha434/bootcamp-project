package com.springboot.ecommerceApplication.domain;

import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class LoginSchedular {
    @Autowired
    UserRepo userRepo;
   //private static final Logger logger = LoggerFactory.getLogger(LoginSchedular.class);
//    private static final DateTimeFormatter dateTimeFormatter =
//            DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRateString = "${scheduler.timeInterval.activateUser}")
    public void unlockAccount() {
//        userRepo.ActivateUsers();
//        List<User> list=  userRepo.findByIsActive(false);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//  List<User> list = new ArrayList();
//        Date currentTime = new Date();
//        Long value;
//        for (User user : list) {
//            value = currentTime.getTime()- user.getDeactivatedTime().getTime();
//           // logger.info("this is a logger{}",);
//          //System.out.println("###########################"+value);
//            if (value >= 86400000 ) {
//                user.setActive(true);
//                userRepo.save(user);
//            }
//        }
    }

}

