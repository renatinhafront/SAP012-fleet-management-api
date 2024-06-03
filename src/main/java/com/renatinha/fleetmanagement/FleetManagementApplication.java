package com.renatinha.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//regra de negocio fica sempre na service

@SpringBootApplication
public class FleetManagementApplication {

	//spring boot SpringApplication = inicializa a aplicação subindo o docker
	public static void main(String[] args) {
		SpringApplication.run(FleetManagementApplication.class, args);
	}

}
