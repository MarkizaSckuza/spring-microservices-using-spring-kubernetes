package com.mynotes.spring.cloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    @Autowired
    ContactUsConfig config;

    @Autowired
    Configuration configuration;

    @Autowired
    RestTemplate rest;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    @ResponseBody
    public String getContactUsDetails() {

        String result = rest.getForObject("http://user-service/user/public-address", String.class);

        return "Contact Address ==> " + result;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public String getConfigFromConfigServer() {
        String result = String.format("Data from Config Server: ReadTO %d and ConnTO %d", configuration.getReadTimeoutMillis(), configuration.getConnectionTimeoutMillis());
        return result;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {

        return "Hello from contact us";
    }


    /*
        By default, should return "Message from contact us service"
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public String message() {
        return config.getMessage();
    }

}