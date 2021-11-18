package com.actividad3.servidorRest.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.actividad3.servidorRest.modelo.VideoJuego;

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

	public VideoJuego añadirVideoJuego(VideoJuego vj) {

		vj = comprobarNombre(vj);
		if(vj!=null) {
			vj.setId(id++);
			listaVideoJuego.add(vj);
		}
	
		return vj;

	}

	public VideoJuego borrarVideoJuego(int id) {

		try {

			System.out.println("Videojuego " + id + " borrado");
			return listaVideoJuego.remove(id);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe videojuego con el id " + id);
			return null;
		}

	}

	public VideoJuego modificarVideoJuego(VideoJuego vj) {
		VideoJuego vjAux = null;
		try {

			vj = comprobarNombre(vj);
			if (vj != null) {

				vjAux = listaVideoJuego.get(vj.getId());
				vjAux.setNombre(vj.getNombre());
				vjAux.setCompañia(vj.getCompañia());
				vjAux.setNota(vj.getNota());
			} else {
				return null;
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No existe el videojuego a modificar");
			return null;
		}
		return vjAux;
	}

	public VideoJuego obtenerVideoJuego(int id) {
		try {

			return listaVideoJuego.get(id);

		} catch (IndexOutOfBoundsException e) {

			return null;
		}
	}

	public List<VideoJuego> listarVideoJuego() {

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
