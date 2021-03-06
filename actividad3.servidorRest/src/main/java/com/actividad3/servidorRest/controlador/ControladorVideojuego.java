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

import com.actividad3.servidorRest.modelo.VideoJuego;
import com.actividad3.servidorRest.persistencia.DaoVideoJuego;

@RestController
public class ControladorVideojuego {

	@Autowired
	private DaoVideoJuego daoVideoJuego;

	// Post VideoJuego

	@PostMapping(path = "videojuegos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> añadirVideoJuego(@RequestBody VideoJuego vj) {

		VideoJuego vjAñadido = daoVideoJuego.añadirVideoJuego(vj);
		if (vjAñadido != null) {
			return new ResponseEntity<VideoJuego>(vj, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.CONFLICT);// El codigo de respuesta puede ser cambiado.
		}

	}

	// Delete VideoJuego

	@DeleteMapping(path = "videojuegos/{id}")
	public ResponseEntity<VideoJuego> borrarVideoJuego(@PathVariable("id") int id) {

		VideoJuego vj = daoVideoJuego.borrarVideoJuego(id);

		if (vj != null) {
			return new ResponseEntity<VideoJuego>(vj, HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "videojuegos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> modificarVideoJuego(@PathVariable("id") int id, @RequestBody VideoJuego vj) {

		vj.setId(id);
		VideoJuego vjModificado = daoVideoJuego.modificarVideoJuego(vj);

		if (vjModificado != null) {
			return new ResponseEntity<VideoJuego>(HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
		}

	}

	// GET videojuego

	@GetMapping(path = "videojuegos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VideoJuego> obtenerVideoJuego(@PathVariable("id") int id) {

		VideoJuego vj = daoVideoJuego.obtenerVideoJuego(id);

		if (vj != null) {
			return new ResponseEntity<VideoJuego>(vj, HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
		}
	}

	// GET Lista de Videojuegos

	@GetMapping(path = "videojuegos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VideoJuego>> ListarVideojuego() {

		List<VideoJuego> listaVideoJuego = daoVideoJuego.listarVideoJuego();

		System.out.println(listaVideoJuego);
		return new ResponseEntity<List<VideoJuego>>(listaVideoJuego, HttpStatus.OK);
	}

}
