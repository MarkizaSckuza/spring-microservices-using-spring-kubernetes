package com.mynotes.spring.cloud.eureka;

import com.mynotes.spring.cloud.eureka.config.ContactUsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @Autowired
    private ContactUsConfig config;

    @Scheduled(fixedDelay = 15000)
    public void hello() {
        System.out.println("The message is: " + config.getMessage());
    }
}
