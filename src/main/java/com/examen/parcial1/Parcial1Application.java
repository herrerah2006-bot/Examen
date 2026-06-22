package com.examen.parcial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.examen.parcial1") // ESTO ES LO QUE TE FALTA
public class Parcial1Application {
    public static void main(String[] args) {
        SpringApplication.run(Parcial1Application.class, args);
    }
}
