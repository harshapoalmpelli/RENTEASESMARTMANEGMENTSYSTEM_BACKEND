package com.rentease;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentEaseApplication {

    public static void main(String[] args) {
        // When deployed on Render:
        if (System.getenv("RENDER") != null) {
            String dbUrl = System.getenv("SPRING_DATASOURCE_URL");
            boolean canConnect = false;

            if (dbUrl != null && !dbUrl.trim().isEmpty()) {
                try {
                    String user = System.getenv("SPRING_DATASOURCE_USERNAME");
                    String pass = System.getenv("SPRING_DATASOURCE_PASSWORD");
                    DriverManager.setLoginTimeout(5);
                    Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                    conn.close();
                    canConnect = true;
                    System.out.println("Successfully connected to external database: " + dbUrl);
                } catch (Exception e) {
                    System.err.println("Could not connect to external DB: " + e.getMessage() + ". Falling back to embedded DB.");
                }
            }

            if (!canConnect) {
                System.setProperty("spring.datasource.url", "jdbc:h2:mem:rentease_db;DB_CLOSE_DELAY=-1;MODE=MySQL");
                System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
                System.setProperty("spring.datasource.username", "sa");
                System.setProperty("spring.datasource.password", "");
            }
        }

        SpringApplication.run(RentEaseApplication.class, args);
        System.out.println("==========================================");
        System.out.println(" RentEase Spring Boot Backend is Running! ");
        System.out.println(" Server: http://localhost:8080            ");
        System.out.println("==========================================");
    }
}
