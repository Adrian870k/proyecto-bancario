package com.banco.clientes.servicio_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = "com.banco.clientes")
@EntityScan(basePackages = "com.banco.clientes.domain.model")
@EnableJpaRepositories(basePackages = "com.banco.clientes.domain.repository")
public class ServicioClientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicioClientesApplication.class, args);
    }
}
