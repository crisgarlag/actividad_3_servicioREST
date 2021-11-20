package com.actividad3.clienteRest.controlador;

import java.util.ArrayList;
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

	public VideoJuego añadirVideoJuego(VideoJuego vj) {

		try {
			ResponseEntity<VideoJuego> respEnti = restTemplate.postForEntity(URL_SERVIDOR, vj, VideoJuego.class);
			HttpStatus httpst = respEnti.getStatusCode();
			if (httpst == HttpStatus.CREATED) {
				System.out.println("El videoJuego llamado " + vj.getNombre() + " se ha dado de alta correctamente");
				System.out.println("Codigo de respuesta: " + respEnti.getStatusCode());
				return respEnti.getBody();
			} else {
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego llamado " + vj.getNombre() + " no se ha dado de alta");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	public boolean borrarVideoJuego(int id) {
		try {
			restTemplate.delete(URL_SERVIDOR + id);
			System.out.println("El videojuego con id " + id + " se ha eliminado.");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego con id " + id + " no se ha encontrado y, por tanto, no se ha dado eliminado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return false;
		}

	}

	public boolean modificarVideoJuego(VideoJuego vj) {
		try {
			restTemplate.put(URL_SERVIDOR + vj.getId(),vj, VideoJuego.class);
			System.out.println("El videoJuego con id " + vj.getId() + " se ha modificado correctamente");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego con id " + vj.getId() + " no se ha dado modificado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	public VideoJuego obtenerVideoJuego(int id) {
		try {
			ResponseEntity<VideoJuego> respEnti = restTemplate.getForEntity(URL_SERVIDOR + id, VideoJuego.class);
			HttpStatus httpst = respEnti.getStatusCode();
			if (httpst == HttpStatus.OK) {
				System.out.println("Este es el videojuego solicitado \n" + respEnti.getBody());
				System.out.println("Codigo de respuesta: " + respEnti.getStatusCode());
				return respEnti.getBody();
			} else {
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("El videoJuego con id " + id + " no se ha localizado");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}
	
	public List<VideoJuego> listarVideoJuegos(){
		
		try{
			ResponseEntity<VideoJuego[]> respEnti = restTemplate.getForEntity(URL_SERVIDOR, VideoJuego[].class);
			VideoJuego[] arrayVj = respEnti.getBody();
			return Arrays.asList(arrayVj);
		}catch(HttpClientErrorException e) {
			System.out.println("No se ha podido obtener la lista de videojuegos disponibles");
			System.out.println("Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
		
	}

}
