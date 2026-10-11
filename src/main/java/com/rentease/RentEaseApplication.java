package com.rentease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentEaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentEaseApplication.class, args);
        System.out.println("==========================================");
        System.out.println(" RentEase Spring Boot Backend is Running! ");
        System.out.println(" Server: http://localhost:8080            ");
        System.out.println(" Connected to MySQL: rentease_db          ");
        System.out.println("==========================================");
    }
}
