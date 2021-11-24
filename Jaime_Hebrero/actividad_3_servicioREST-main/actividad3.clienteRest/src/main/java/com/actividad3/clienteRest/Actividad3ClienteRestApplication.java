package com.actividad3.clienteRest;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.actividad3.clienteRest.controlador.MenuUsuarioVideoJuego;
import com.actividad3.clienteRest.controlador.ServicioProxiVideoJuego;
import com.actividad3.clienteRest.modelo.VideoJuego;

@SpringBootApplication
public class Actividad3ClienteRestApplication implements CommandLineRunner {

	@Autowired
	private VideoJuego vidj;
	
	@Autowired
	private ServicioProxiVideoJuego spvj;
	
	@Autowired
	private ApplicationContext contextoSpring;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

		return builder.build();

	}

	public static void main(String[] args) {
		SpringApplication.run(Actividad3ClienteRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Scanner sc= new Scanner(System.in);
		String opcion = null;
		String id;
		boolean opcionIncorrecta = false;
		
		do {
			//------------------------------------------------------MENU--------------------------------------------------------------//	
			System.out.println("---------MENU ---------\n");
			System.out.println("Seleecione una opcion.");
			System.out.println("1.- Añadir un videojuego");
			System.out.println("2.- Borrar un videojuego por ID");
			System.out.println("3.- Modificar un videojuego por ID");
			System.out.println("4.- Listar un videojuego por ID");
			System.out.println("5.- Listar todos los videojuegos");
			System.out.println("6.- Salir");

			opcion = sc.nextLine();
			while (opcionIncorrecta == false) {
				if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")
						|| opcion.equals("5") || opcion.equals("6")) {
					opcionIncorrecta = true;
				} else {
					System.out.println("Dato incorrecto, intentelo de nuevo.");
					opcion = sc.nextLine();
				}
				
			}
			opcionIncorrecta=false;
			try {
				switch (opcion) {

				case "1":
					System.out.println("¿Cual es el nombre del videojuego?");
					vidj.setNombre(sc.nextLine());
					System.out.println("¿Cual es el nombre de la compañia?");
					vidj.setCompañia(sc.nextLine());
					System.out.println("Escribe la nota del videojuego");
					vidj.setNota(sc.nextFloat());
					spvj.añadirVideoJuego(vidj);			
					break;
				case "2":
					System.out.println("¿Cual es el id del videojuego?");
					id= sc.nextLine();
					spvj.borrarVideoJuego(Integer.parseInt(id));
					break;
				case "3":
					System.out.println("¿Cual es el id del videojuego?");
					id= sc.nextLine();
					vidj.setId(Integer.parseInt(id));
					System.out.println("¿Cual es el nombre del videojuego?");
					vidj.setNombre(sc.nextLine());
					System.out.println("¿Cual es el nombre de la compañia?");
					vidj.setCompañia(sc.nextLine());
					System.out.println("Escribe la nota del videojuego");
					vidj.setNota(sc.nextFloat());
					spvj.modificarVideoJuego(vidj);
					break;
				case "4":
					System.out.println("¿Cual es el id del videojuego?");
					id= sc.nextLine();
					spvj.obtenerVideoJuego(Integer.parseInt(id));
					break;
				case "5":
					System.out.println(spvj.listarVideoJuegos());
					break;
				}
				
			}catch(InputMismatchException e) {
				System.out.println("La nota introducicida no es correcta, introduce una nota válida");
			} catch(NumberFormatException e1 ) {
				System.out.println("El id introducido no es correcto, introduce un valor válido");
			}
			
		}	while(!opcion.equals("6"));
	
		System.out.println("FIN DE LA APLICACION");	
		salirApp();
		sc.close();
	}

	public void salirApp() {

		SpringApplication.exit(contextoSpring, () -> 0);
	}

}
