package com.actividad3.servidorRest.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.actividad3.servidorRest.entidad.VideoJuego;
import com.actividad3.servidorRest.persistencia.DaoVideoJuego;

/**
 * CRUD de videojuegos en el servidor.
 * 
 * @author cristiangarcialagar
 *
 */
@RestController
public class ControladorVideojuego {
	/**
	 * Inyeccion de un objeto daoVideoJuego en el contexto de Spring
	 */
	@Autowired
	private DaoVideoJuego daoVideoJuego;

	/**
	 * Metodo para dar de alta un videojuego utilizando el metodo POST, le pasamos
	 * un JSON y nos lo devuelve en el mismo formato
	 * 
	 * @param vj es el videojuego pasado en el cuerpo de la consulta para darlo de
	 *           alta
	 * @return En respuesta, si vjAñadido != null, el videojuego en formato JSON y
	 *         el codigo Created de lo contrario el codigo Forbidden
	 */
	@PostMapping(path = "videojuegos/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> añadirVideoJuego(@RequestBody VideoJuego vj) {

		VideoJuego vjAñadido = daoVideoJuego.añadirVideoJuego(vj);
		if (vjAñadido != null) {
			return new ResponseEntity<VideoJuego>(vj, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * Metodo para eliminar un videojuego utilizando el metodo DELETE, le pasamos un
	 * el id del videojuego se va a borrar
	 * 
	 * @param id del videojuego que se va a borrar
	 * @return En respuesta, si borrado == true, el codigo OK de lo contrario el
	 *         codigo NOT FOUND
	 */
	@DeleteMapping(path = "videojuegos/{id}")
	public ResponseEntity<VideoJuego> borrarVideoJuego(@PathVariable("id") int id) {

		Boolean borrado = daoVideoJuego.borrarVideoJuego(id);

		if (borrado == true) {
			return new ResponseEntity<VideoJuego>(HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para modificar un videojuego utilizando el metodo PUT, le pasamos un
	 * el id del videojuego se va a modificar y en el requestbody el videojuego
	 * con los datos a modificar, en formato JSON
	 * 
	 * @param id del videojuego a modificar
	 * @param vj objeto que contiene los datos a modificar del videojuego
	 * @return En respuesta, si vjModificado != null, el codigo OK de lo contrario el
	 *         codigo NOT FOUND
	 */
	@PutMapping(path = "videojuegos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> modificarVideoJuego(@PathVariable("id") int id, @RequestBody VideoJuego vj) {

		vj.setId(id);
		VideoJuego vjModificado = daoVideoJuego.modificarVideoJuego(vj);

		if (vjModificado != null) {
			return new ResponseEntity<VideoJuego>(HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Metodo para obtener un videojuego utilizando el metodo GET, le pasamos un
	 * el id del videojuego a buscar y nos devuevlve el videojuego en formato JSON
	 * @param id del videojuego buscado
	 * @return En respuesta, si vj != null, el videojuego en formato JSON y
	 *         el codigo OK de lo contrario el codigo Not Found
	 */
	@GetMapping(path = "videojuegos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> obtenerVideoJuego(@PathVariable("id") int id) {

		VideoJuego vj = daoVideoJuego.obtenerVideoJuego(id);

		if (vj != null) {
			return new ResponseEntity<VideoJuego>(vj, HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para obtener una lista con todos los videojuego utilizando el metodo GET, le pasamos un
	 * nos devuevlve la lista en formato JSON
	 * @return En respuesta, la lista en formato JSON y el codigo OK
	 */
	@GetMapping(path = "videojuegos/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VideoJuego>> ListarVideojuego() {

		List<VideoJuego> listaVideoJuego = daoVideoJuego.listarVideoJuegos();

		System.out.println(listaVideoJuego);
		return new ResponseEntity<List<VideoJuego>>(listaVideoJuego, HttpStatus.OK);
	}

}
