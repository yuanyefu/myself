package com.docker.yyf;

import com.docker.yyf.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 18329
 */
@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableAsync
@ComponentScan


public class DockerApplication {

    @Autowired
    private ICustomerManager customerManager;

    @RequestMapping("/")
    public String home() {
        String test = customerManager.test();
        System.out.println(test);
        return test;
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class, args);
    }
}
