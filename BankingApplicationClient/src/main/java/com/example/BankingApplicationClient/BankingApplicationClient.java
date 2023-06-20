package com.example.BankingApplicationClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankingApplicationClient {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplicationClient.class, args);
    }

}
