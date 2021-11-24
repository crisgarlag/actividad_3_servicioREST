package servicioRest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import servicioRest.modelo.entidad.Videojuego;

@Component
public class DaoVideojuego {
	
	public List<Videojuego> listajuegos;
	public int contador;
	

	public DaoVideojuego() {
		System.out.println("DaoVideojuego -> Creando videojuegos...");
		listajuegos = new ArrayList<Videojuego>();
		Videojuego v1 = new Videojuego(contador++, "Fifa 22", "EA SPORTS", 7);
		Videojuego v2 = new Videojuego(contador++, "Age of Empires IV", "Relic Entertainment", 9.75);
		Videojuego v3 = new Videojuego(contador++, "StarCraft", "Blizzard Entertainment", 8);
		Videojuego v4 = new Videojuego(contador++, "Civilization VI", "Firaxis Games", 7.5);
		Videojuego v5 = new Videojuego(contador++, "Counter-Strike: Global Offensive", "Valve Corpotarion", 10);
		
		listajuegos.add(v1);
		listajuegos.add(v2);
		listajuegos.add(v3);
		listajuegos.add(v4);
		listajuegos.add(v5);
	}
	
	public Videojuego get(int posicion) {
		try {
			return listajuegos.get(posicion);
		}catch (IndexOutOfBoundsException iobe) {
			System.out.println("Videojuego fuera de rango");
			return null;
		}
	}
	
	public List<Videojuego> list(){
		return listajuegos;
	}
	
	public void add(Videojuego v) {
		v.setId(contador++);
		listajuegos.add(v);
	}
	
	public Videojuego delete(int posicion) {
		try {
			return listajuegos.remove(posicion);
		}catch (Exception e) {
			System.out.println("Delete -> Fuera de rango");
			return null;
		}
	}
	
	public Videojuego update(Videojuego v) {
		try {
			Videojuego vAux = listajuegos.get(v.getId());
			vAux.setNombre(v.getNombre());
			vAux.setCompañia(v.getCompañia());
			vAux.setNota(v.getNota());
			return vAux;
		}catch (IndexOutOfBoundsException iobe) {
			System.out.println("Update -> Videojuego fuera de rango");
			return null;
		}
	}
	
	public List<Videojuego> listarPorNombre(String nombre){
		List<Videojuego> listaJuegosAux = new ArrayList<Videojuego>();
		for(Videojuego v: listajuegos) {
			if(v.getNombre().equalsIgnoreCase(nombre)) {
				listaJuegosAux.add(v);
			}
		}
		return listaJuegosAux;
	}
}
