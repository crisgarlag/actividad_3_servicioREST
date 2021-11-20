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
	private VideoJuego vj;
	@Autowired
	private ServicioProxiVideoJuego spvj;

	public String menu() {
		
		String eleccion = null;
		boolean opcionIncorrecta = false;

		System.out.println("---------MENU DE VIDEOJUEGOS---------\n");
		System.out.println("Seleecione una de las siguientes opciones.");
		System.out.println("1.- Dar de alta un videojuego");
		System.out.println("2.- Dar de baja un videojuego por ID");
		System.out.println("3.- Modificar un videojuego por ID");
		System.out.println("4.- Obtener un videojuego por ID");
		System.out.println("5.- Listar todos los videojuegos");
		System.out.println("6.- Salir de la aplicacion");

		eleccion = sc1.nextLine();
		while (opcionIncorrecta == false) {
			if (eleccion.equals("1") || eleccion.equals("2") || eleccion.equals("3") || eleccion.equals("4")
					|| eleccion.equals("5") || eleccion.equals("6")) {
				opcionIncorrecta = true;
			} else {
				System.out.println("El dato introducido es incorrecto, introduce una de las opciones validas.");
				eleccion = sc1.nextLine();

			}
		}
		return eleccion;
	}

	/**
	 * Segñun la eleccion introducida por parametros muestra por solicita por
	 * pantalla la informacion que se enviara al servidor.
	 * 
	 * @param eleccion
	 * @return La informacion de la que el usuario quiere obtener informacion.
	 */
	public void realizarOpcionElegida(String eleccion) {
		
try {
	switch (eleccion) {

	case "1":
		System.out.println("Introduzca el nombre del videojuego");
		vj.setNombre(sc2.nextLine());
		System.out.println("Introduzca el nombre de la compañia");
		vj.setCompañia(sc2.nextLine());
		System.out.println("Introduzca la nota del videojuego");
		vj.setNota(sc2.nextFloat());
		spvj.añadirVideoJuego(vj);			
		break;
	case "2":
		System.out.println("Introduzca el id del videojuego");
		spvj.borrarVideoJuego(sc2.nextInt());
		break;
	case "3":
		System.out.println("Introduzca el id del videojuego");
		vj.setId(sc2.nextInt());
		System.out.println("Introduzca el nombre del videojuego");
		vj.setNombre(sc2.nextLine());
		System.out.println("Introduzca el nombre de la compañia");
		vj.setCompañia(sc2.nextLine());
		System.out.println("Introduzca la nota del videojuego");
		vj.setNota(sc2.nextFloat());
		spvj.modificarVideoJuego(vj);
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
