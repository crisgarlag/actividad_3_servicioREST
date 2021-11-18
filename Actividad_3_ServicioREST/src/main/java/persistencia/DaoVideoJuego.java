package persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import modelo.VideoJuego;

@Component
public class DaoVideoJuego {

	private List<VideoJuego> listaVideoJuego;
	private int id;

	public DaoVideoJuego() {
		System.out.println("Creando Lista de personas");
		listaVideoJuego = new ArrayList<VideoJuego>();
		VideoJuego vj0 = new VideoJuego(id++, "vj0", "comp0", 1F);
		VideoJuego vj1 = new VideoJuego(id++, "vj0", "comp0", 1F);
		VideoJuego vj2 = new VideoJuego(id++, "vj0", "comp0", 1F);
		VideoJuego vj3 = new VideoJuego(id++, "vj0", "comp0", 1F);
		VideoJuego vj4 = new VideoJuego(id++, "vj0", "comp0", 1F);
		listaVideoJuego.add(vj0);
		listaVideoJuego.add(vj1);
		listaVideoJuego.add(vj2);
		listaVideoJuego.add(vj3);
		listaVideoJuego.add(vj4);
	}

	public void añadirVideoJuego(VideoJuego vj) {
		vj.setId(id++);
		listaVideoJuego.add(vj);
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
		try {
			
			VideoJuego vjAux = listaVideoJuego.get(vj.getId());
			vjAux.setNombre(vj.getNombre());
			vjAux.setCompañia(vj.getCompañia());
			vjAux.setNota(vj.getNota());
			return vjAux;
			
			
		}catch (IndexOutOfBoundsException e) {
			System.out.println("No existe el videojuego a modificar");
			return null;
		}
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

}
