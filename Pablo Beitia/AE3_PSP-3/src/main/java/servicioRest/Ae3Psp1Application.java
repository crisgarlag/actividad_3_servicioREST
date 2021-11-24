package servicioRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ae3Psp1Application {

	public static void main(String[] args) {
		System.out.println("ServicioRest -> Cargando el contexto Spring...");
		SpringApplication.run(Ae3Psp1Application.class, args);
		System.out.println("Servicio Rest -> Contento de Spring cargado!");
	}

}
