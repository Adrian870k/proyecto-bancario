package com.banco.clientes.servicio_cuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.banco.cuentas")
@EntityScan(basePackages = "com.banco.cuentas.domain.model")
@EnableJpaRepositories(basePackages = "com.banco.cuentas.domain.repository")
public class ServicioCuentasApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicioCuentasApplication.class, args);
    }
}
