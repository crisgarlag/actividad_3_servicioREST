package com.actividad3.servidorRest.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.actividad3.servidorRest.modelo.VideoJuego;

@Component
public class DaoVideoJuego {

	private List<VideoJuego> listaVideoJuego;
	private int id;
	
	public DaoVideoJuego() {													//Inicializamos el DaoVideojuego con datos ya cargados
		System.out.println("Creando Lista de VideoJuegos");
		listaVideoJuego = new ArrayList<VideoJuego>();
		VideoJuego vj0 = new VideoJuego(id++,"The last of us", "Naugthy dog", 98);
		VideoJuego vj1 = new VideoJuego(id++,"Uncharted", "Naugthy dog", 95);
		VideoJuego vj2 = new VideoJuego(id++,"Cod", "Activision", 74);
		VideoJuego vj3 = new VideoJuego(id++,"Destiny", "Bungie", 85);
		VideoJuego vj4 = new VideoJuego(id++,"The Witcher3", "CD Proyect", 96);
		listaVideoJuego.add(vj0);
		listaVideoJuego.add(vj1);
		listaVideoJuego.add(vj2);
		listaVideoJuego.add(vj3);
		listaVideoJuego.add(vj4);
	}
	
	
	
	public VideoJuego obtenerVideoJuego(int id) {								//Devuelve un videojuego a partir de su posicion del array
		try {

			return listaVideoJuego.get(id);

		} catch (IndexOutOfBoundsException e) {

			return null;
		}
	}
	
	
	
	public List<VideoJuego> listarVideoJuego() {								//Metodo que devuelve todos los videojuegos del array

		return listaVideoJuego;
	}

	
	public VideoJuego añadirVideoJuego(VideoJuego vidj) {						//Metodo que introduce un videojuego al final de la lista

		vidj = comprobarNombre(vidj);
		if(vidj!=null) {
			vidj.setId(id++);
			listaVideoJuego.add(vidj);
		}
	
		return vidj;

	}

	public VideoJuego borrarVideoJuego(int id) {								//Metodo que borra un videojuego de la lista

		try {

			System.out.println("El videojuego " + id + " ha sido borrado");
			return listaVideoJuego.remove(id);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe videojuego con el id " + id);
			return null;
		}

	}

	public VideoJuego modificarVideoJuego(VideoJuego vidj) {					//Metodo para modificar un videojuego de la lista
		VideoJuego vjAux = null;
		try {

			vidj = comprobarNombre(vidj);
			if (vidj != null) {

				vjAux = listaVideoJuego.get(vidj.getId());
				vjAux.setNombre(vidj.getNombre());
				vjAux.setCompañia(vidj.getCompañia());
				vjAux.setNota(vidj.getNota());
			} else {
				return null;
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe este videojuego");
			return null;
		}
		return vjAux;
	}

	

	
	public VideoJuego comprobarNombre(VideoJuego vidj) {						//Metodo para comprobar si un juego ya esta en la lista

		for (int i = 0; i < listaVideoJuego.size(); i++) {
			if (vidj.getNombre().equals(listaVideoJuego.get(i).getNombre())) {
				System.out.println("No se aceptan dos videojuegos con el mismo nombre");
				return null;
			}
		}
		return vidj;
	}

}
