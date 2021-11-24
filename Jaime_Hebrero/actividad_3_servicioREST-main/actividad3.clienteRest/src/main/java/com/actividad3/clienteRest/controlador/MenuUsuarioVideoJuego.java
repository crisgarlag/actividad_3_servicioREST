package com.actividad3.clienteRest.controlador;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.actividad3.clienteRest.modelo.VideoJuego;

@Component
public class MenuUsuarioVideoJuego {
	
	private Scanner sc1 = new Scanner(System.in);
	private Scanner sc2 = new Scanner(System.in);
	
	@Autowired
	private VideoJuego vidj;
	@Autowired
	private ServicioProxiVideoJuego spvj;
	//------------------------------------------------------MENU--------------------------------------------------------------//
	public String menu() {
		
		String opcion = null;
		boolean opcionIncorrecta = false;

		System.out.println("---------MENU ---------\n");
		System.out.println("Seleecione una opcion.");
		System.out.println("1.- Añadir un videojuego");
		System.out.println("2.- Borrar un videojuego por ID");
		System.out.println("3.- Modificar un videojuego por ID");
		System.out.println("4.- Listar un videojuego por ID");
		System.out.println("5.- Listar todos los videojuegos");
		System.out.println("6.- Salir");

		opcion = sc1.nextLine();
		while (opcionIncorrecta == false) {
			if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")
					|| opcion.equals("5") || opcion.equals("6")) {
				opcionIncorrecta = true;
			} else {
				System.out.println("El dato introducido es incorrecto, introduce una de las opciones validas.");
				opcion = sc1.nextLine();

			}
		}
		return opcion;
	}

	
	public void realizarOpcionElegida(String opcion) {
		
try {
	switch (opcion) {

	case "1":
		System.out.println("Introduzca el nombre del videojuego");
		vidj.setNombre(sc2.nextLine());
		System.out.println("Introduzca el nombre de la compañia");
		vidj.setCompañia(sc2.nextLine());
		System.out.println("Introduzca la nota del videojuego");
		vidj.setNota(sc2.nextFloat());
		spvj.añadirVideoJuego(vidj);			
		break;
	case "2":
		System.out.println("Introduzca el id del videojuego");
		spvj.borrarVideoJuego(sc2.nextInt());
		break;
	case "3":
		System.out.println("Introduzca el id del videojuego");
		vidj.setId(sc2.nextInt());
		System.out.println("Introduzca el nombre del videojuego");
		vidj.setNombre(sc2.nextLine());
		System.out.println("Introduzca el nombre de la compañia");
		vidj.setCompañia(sc2.nextLine());
		System.out.println("Introduzca la nota del videojuego");
		vidj.setNota(sc2.nextFloat());
		spvj.modificarVideoJuego(vidj);
		break;
	case "4":
		System.out.println("Introduzca el id del videojuego");
		spvj.obtenerVideoJuego(sc2.nextInt());
		break;
	case "5":
		System.out.println(spvj.listarVideoJuegos());
		break;
	}
}catch(IllegalStateException e) {
	System.out.println("El valor introducido no es correcto");
}
	}	

}
