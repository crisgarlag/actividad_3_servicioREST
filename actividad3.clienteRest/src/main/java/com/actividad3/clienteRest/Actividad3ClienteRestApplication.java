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
	private VideoJuego vj;
	
	@Autowired
	private ServicioProxiVideoJuego spvj;
	
	//@Autowired
	//private MenuUsuarioVideoJuego menuUsuarioVideoJuego;

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
		String eleccion = null;
		String id;
		boolean opcionIncorrecta = false;
		
		do {
			
			System.out.println("---------MENU DE VIDEOJUEGOS---------\n");
			System.out.println("Seleecione una de las siguientes opciones.");
			System.out.println("1.- Dar de alta un videojuego");
			System.out.println("2.- Dar de baja un videojuego por ID");
			System.out.println("3.- Modificar un videojuego por ID");
			System.out.println("4.- Obtener un videojuego por ID");
			System.out.println("5.- Listar todos los videojuegos");
			System.out.println("6.- Salir de la aplicacion");

			eleccion = sc.nextLine();
			while (opcionIncorrecta == false) {
				if (eleccion.equals("1") || eleccion.equals("2") || eleccion.equals("3") || eleccion.equals("4")
						|| eleccion.equals("5") || eleccion.equals("6")) {
					opcionIncorrecta = true;
				} else {
					System.out.println("El dato introducido es incorrecto, introduce una de las opciones validas.");
					eleccion = sc.nextLine();
				}
				
			}
			opcionIncorrecta=false;
			try {
				switch (eleccion) {

				case "1":
					System.out.println("Introduzca el nombre del videojuego");
					vj.setNombre(sc.nextLine());
					System.out.println("Introduzca el nombre de la compañia");
					vj.setCompañia(sc.nextLine());
					System.out.println("Introduzca la nota del videojuego");
					vj.setNota(sc.nextFloat());
					spvj.añadirVideoJuego(vj);			
					break;
				case "2":
					System.out.println("Introduzca el id del videojuego");
					id= sc.nextLine();
					spvj.borrarVideoJuego(Integer.parseInt(id));
					break;
				case "3":
					System.out.println("Introduzca el id del videojuego");
					id= sc.nextLine();
					vj.setId(Integer.parseInt(id));
					System.out.println("Introduzca el nombre del videojuego");
					vj.setNombre(sc.nextLine());
					System.out.println("Introduzca el nombre de la compañia");
					vj.setCompañia(sc.nextLine());
					System.out.println("Introduzca la nota del videojuego");
					vj.setNota(sc.nextFloat());
					spvj.modificarVideoJuego(vj);
					break;
				case "4":
					System.out.println("Introduzca el id del videojuego");
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
			
		}	while(!eleccion.equals("6"));
	
		System.out.println("FIN DE LA APLICACION");	
		salirApp();
		sc.close();
	}

	public void salirApp() {

		SpringApplication.exit(contextoSpring, () -> 0);
	}

}
