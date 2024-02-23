package com.pidev.backend;

import com.pidev.backend.Service.ReclamationService;
import com.pidev.backend.ServiceImpl.ReclamationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BackendPidevApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendPidevApplication.class, args);
    }

}
