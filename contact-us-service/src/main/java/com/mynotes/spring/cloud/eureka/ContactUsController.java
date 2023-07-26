package com.mynotes.spring.cloud.eureka;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    @Autowired
    ContactUsConfig config;

    @Autowired
    Configuration configuration;

    @Autowired
    RestTemplate rest;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public Iterable<String> findAll() {
        return discoveryClient.getServices();
    }

    @GetMapping("/service")
    public List<ServiceInstance> findOne() {
        return discoveryClient.getInstances("config-service");
    }

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
