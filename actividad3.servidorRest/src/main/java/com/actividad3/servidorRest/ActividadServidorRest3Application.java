package com.actividad3.servidorRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActividadServidorRest3Application {

	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		
		SpringApplication.run(ActividadServidorRest3Application.class, args);
		
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}

}
