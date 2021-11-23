package com.actividad3.servidorRest.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.actividad3.servidorRest.entidad.VideoJuego;

/**
 * Clase en la que se encuentran almacenados los videojuegos y contiene los
 * metodos para realizar un CRUD sobre la lista de videojuegos
 * 
 * @author cristiangarcialagar
 *
 */
@Component
public class DaoVideoJuego {

	private List<VideoJuego> listaVideoJuego;
	private int id;

	public DaoVideoJuego() {
		System.out.println("Creando Lista de VideoJuegos");
		listaVideoJuego = new ArrayList<VideoJuego>();
		VideoJuego vj0 = new VideoJuego(id++, "vj0", "comp0", 1F);
		VideoJuego vj1 = new VideoJuego(id++, "vj1", "comp1", 2F);
		VideoJuego vj2 = new VideoJuego(id++, "vj2", "comp2", 3F);
		VideoJuego vj3 = new VideoJuego(id++, "vj3", "comp3", 4F);
		VideoJuego vj4 = new VideoJuego(id++, "vj4", "comp4", 5F);
		listaVideoJuego.add(vj0);
		listaVideoJuego.add(vj1);
		listaVideoJuego.add(vj2);
		listaVideoJuego.add(vj3);
		listaVideoJuego.add(vj4);
	}

	/**
	 * Añade videojuegos a la lista
	 * 
	 * @param vj es el videojuego a introducir
	 * @return el vj introducido
	 */
	public VideoJuego añadirVideoJuego(VideoJuego vj) {

		vj = comprobarNombre(vj);
		if (vj != null) {
			vj.setId(id++);
			listaVideoJuego.add(vj);
			System.out.println("Se ha añadido el videojuego de nombre " + vj.getNombre());
		}

		return vj;

	}

	/**
	 * Borra un videojuego de la lista por id
	 * 
	 * @param id del videojuego a borrar
	 * @return true si se borra y false si no
	 */
	public boolean borrarVideoJuego(int id) {

		try {

			for (int i = 0; i < listaVideoJuego.size(); i++) {
				if (listaVideoJuego.get(i).getId() == id) {

					System.out.println("Videojuego con id " + id + " borrado");

					return listaVideoJuego.remove(listaVideoJuego.get(i));
				}
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe videojuego con el id " + id);
			return false;
		}
		return false;

	}

	/**
	 * Modifica el videojuego pasado por el id de este
	 * 
	 * @param vj contiene los datos a modificar del videojuego de la lista
	 * @return el videojuego si se modifica, de lo contrario deuvelve null
	 */
	public VideoJuego modificarVideoJuego(VideoJuego vj) {
		VideoJuego vjAux = null;
		try {

			vj = comprobarNombre(vj);
			if (vj != null) {
				for (int i = 0; i < listaVideoJuego.size(); i++) {
					if (listaVideoJuego.get(i).getId() == vj.getId()) {
						vjAux = listaVideoJuego.get(i);
						vjAux.setNombre(vj.getNombre());
						vjAux.setCompañia(vj.getCompañia());
						vjAux.setNota(vj.getNota());
					}
				}
			} else {
				return null;
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe el videojuego a modificar");
			return null;
		}
		return vjAux;
	}

	/**
	 * Obtiene un videojuego por id
	 * 
	 * @param id del videojuego buscado
	 * @return el videojuego si se encuentra en la lista y null si no lo encuentra
	 *
	 */
	public VideoJuego obtenerVideoJuego(int id) {
		try {

			for (int i = 0; i < listaVideoJuego.size(); i++) {
				if (listaVideoJuego.get(i).getId() == id) {
					return listaVideoJuego.get(i);
				}

			}

		} catch (IndexOutOfBoundsException e) {

			return null;
		}
		return null;
	}
	/**
	 * 
	 * @return la lista de videojuegos
	 */
	public List<VideoJuego> listarVideoJuegos() {

		return listaVideoJuego;
	}

	public VideoJuego comprobarNombre(VideoJuego vj) {

		for (int i = 0; i < listaVideoJuego.size(); i++) {
			if (vj.getNombre().equals(listaVideoJuego.get(i).getNombre())) {
				System.out.println("No pueden existir dos videojuegos con el mismo nombre");
				return null;
			}
		}
		return vj;
	}

}
