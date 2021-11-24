package com.actividad3.clienteRest.controlador;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.actividad3.clienteRest.modelo.VideoJuego;

@Service
public class ServicioProxiVideoJuego {

	public static String URL_SERVIDOR = "http://localhost:8080/videojuegos/";

	@Autowired
	private RestTemplate restTemplate;
//amadir un juego
	public VideoJuego añadirVideoJuego(VideoJuego vidj) {

		try {
			ResponseEntity<VideoJuego> respEnti = restTemplate.postForEntity(URL_SERVIDOR, vidj, VideoJuego.class);
			HttpStatus httpst = respEnti.getStatusCode();
			if (httpst == HttpStatus.CREATED) {
				System.out.println("El videoJuego  " + vidj.getNombre() + " ha sido dado de alta");
				System.out.println("Codigo de respuesta: " + respEnti.getStatusCode());
				return respEnti.getBody();
			} else {
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego " + vidj.getNombre() + " no ha sido dado de alta");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}
//borrar juego
	public boolean borrarVideoJuego(int id) {
		try {
			restTemplate.delete(URL_SERVIDOR + id);
			System.out.println("El videojuego cuyo id es " + id + " ha sido eliminado.");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego cuyo id es " + id + " no se ha encontrado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return false;
		}

	}
//modificar juego 
	public boolean modificarVideoJuego(VideoJuego vidj) {
		try {
			restTemplate.put(URL_SERVIDOR + vidj.getId(),vidj, VideoJuego.class);
			System.out.println("El videojuego cuyo id es " + vidj.getId() + " se ha modificado correctamente");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego cuyo id es " + vidj.getId() + " no se ha modificado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}
//obtener videoJuego
	public VideoJuego obtenerVideoJuego(int id) {
		try {
			ResponseEntity<VideoJuego> respEnti = restTemplate.getForEntity(URL_SERVIDOR + id, VideoJuego.class);
			HttpStatus httpst = respEnti.getStatusCode();
			if (httpst == HttpStatus.OK) {
				System.out.println("Videojuego solicitado: \n" + respEnti.getBody());
				System.out.println("Codigo de respuesta: " + respEnti.getStatusCode());
				return respEnti.getBody();
			} else {
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego cuyo id es " + id + " no se ha encontrado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}
//listar
	public List<VideoJuego> listarVideoJuegos(){
		
		try{
			ResponseEntity<VideoJuego[]> respEnti = restTemplate.getForEntity(URL_SERVIDOR, VideoJuego[].class);
			VideoJuego[] arrayVidj = respEnti.getBody();
			return Arrays.asList(arrayVidj);
		}catch(HttpClientErrorException e) {
			System.out.println("No se ha podido obtener la lista de videojuegos.");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
		
	}

}
