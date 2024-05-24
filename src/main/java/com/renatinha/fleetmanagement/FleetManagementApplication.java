package com.renatinha.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FleetManagementApplication {

	//spring boot SpringApplication = inicializa a aplicação subindo o docker
	public static void main(String[] args) {
		SpringApplication.run(FleetManagementApplication.class, args);
	}

}
