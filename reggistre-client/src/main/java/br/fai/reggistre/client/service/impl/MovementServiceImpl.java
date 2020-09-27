package br.fai.reggistre.client.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Movimentacao;

@Service
public class MovementServiceImpl implements MovementService {

	@Override
	public List<Movimentacao> readAll() {

		List<Movimentacao> response = null;

		String endPoint = "http://localhost:8082/api/v1/movement/read-all";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			ResponseEntity<Movimentacao[]> requestResponse = restTemplate.exchange(endPoint, HttpMethod.GET,
					requestEntity, Movimentacao[].class);

			Movimentacao[] movement = requestResponse.getBody();
			response = Arrays.asList(movement);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean create(Movimentacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movimentacao readById(Long id) {
		Movimentacao response = null;

		String endPoint = "http://localhost:8082/api/v1/movement/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			ResponseEntity<Movimentacao> requestResponse = restTemplate.exchange(endPoint, HttpMethod.GET,
					requestEntity, Movimentacao.class);

			
			response = requestResponse.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Movimentacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
