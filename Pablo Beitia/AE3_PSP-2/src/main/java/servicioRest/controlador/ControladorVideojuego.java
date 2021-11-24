package servicioRest.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import servicioRest.modelo.entidad.Videojuego;
import servicioRest.modelo.persistencia.DaoVideojuego;

@RestController
public class ControladorVideojuego {
	
	public ControladorVideojuego() {
		super();
	}

	@Autowired
	private DaoVideojuego daoVideojuego;
	
	@GetMapping(path="videojuegos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> getVideojuego(@PathVariable("id") int id) {
		System.out.println("Buscando Videojuego con id: " + id);
		Videojuego v = daoVideojuego.get(id);
		if(v != null){
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="videojuegos",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> altaVideojuego(@RequestBody Videojuego v) {
		if(daoVideojuego.comprobar(v) == true) {
			System.out.println("El Videojuego no se ha añadido");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			System.out.println("altaVideojuego: objeto Videojuego: " + v);
			daoVideojuego.add(v);
			return new ResponseEntity<Videojuego>(v,HttpStatus.CREATED);//201 CREATED
		}

	}
	
	@GetMapping(path="videojuegos",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Videojuego>> listarJuegos(
			@RequestParam(name="nombre",required=false) String nombre) {
		List<Videojuego> listaJuegos = null;
		//Si no me viene el nombre, devolvemos toda la lista
		if(nombre == null) {
			System.out.println("Listando los juegos");
			listaJuegos = daoVideojuego.list();	
		}else {
			System.out.println("Listando los juegos por nombre: " + nombre);
			listaJuegos = daoVideojuego.listarPorNombre(nombre);
		}
		System.out.println(listaJuegos);
		return new ResponseEntity<List<Videojuego>>(listaJuegos,HttpStatus.OK);
	}
	
	@PutMapping(path="videojuegos/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> modificarVideojuego(
			@PathVariable("id") int id, 
			@RequestBody Videojuego v) {
		
		if(daoVideojuego.comprobar(v) == true) {
			System.out.println("El Videojuego no se ha modificado");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			System.out.println("ID a modificar: " + id);
			System.out.println("Datos a modificar: " + v);
			v.setId(id);
			Videojuego vUpdate = daoVideojuego.update(v);
			if(vUpdate != null) {
				return new ResponseEntity<Videojuego>(HttpStatus.OK);
			}else {
				return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
			}
		}		
	}
	
	@DeleteMapping(path="videojuegos/{id}")
	public ResponseEntity<Videojuego> eliminarVideojuego(@PathVariable("id") int id) {
		System.out.println("Borra el id: " + id);
		Videojuego v = daoVideojuego.delete(id);
		if(v != null) {
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
}
