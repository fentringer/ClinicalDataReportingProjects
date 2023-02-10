package com.fentringer.clinicals;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ClinicalsapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicalsapiApplication.class, args);
    }

}
