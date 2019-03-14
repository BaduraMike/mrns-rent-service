package com.soft.mikessolutions.rentservice;

import com.soft.mikessolutions.rentservice.exceptions.CustomErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class RentServiceApplication {

    @Bean
    public CustomErrorDecoder stashErrorDecoder() {
        return new CustomErrorDecoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(RentServiceApplication.class, args);
    }

}

