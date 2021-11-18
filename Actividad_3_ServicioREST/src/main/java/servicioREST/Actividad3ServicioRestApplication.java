package servicioREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Actividad3ServicioRestApplication {

	public static void main(String[] args) {
		
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		
		
		SpringApplication.run(Actividad3ServicioRestApplication.class, args);
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}

}
