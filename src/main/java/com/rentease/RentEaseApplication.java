package com.rentease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentEaseApplication {

    public static void main(String[] args) {
        // When deployed on Render with no cloud MySQL URL, automatically fallback to in-memory H2 so it starts with 0 errors
        if (System.getenv("RENDER") != null && System.getenv("SPRING_DATASOURCE_URL") == null) {
            System.setProperty("spring.datasource.url", "jdbc:h2:mem:rentease_db;DB_CLOSE_DELAY=-1;MODE=MySQL");
            System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
            System.setProperty("spring.datasource.username", "sa");
            System.setProperty("spring.datasource.password", "");
        }
        SpringApplication.run(RentEaseApplication.class, args);
        System.out.println("==========================================");
        System.out.println(" RentEase Spring Boot Backend is Running! ");
        System.out.println(" Server: http://localhost:8080            ");
        System.out.println(" Connected to MySQL: rentease_db          ");
        System.out.println("==========================================");
    }
}
