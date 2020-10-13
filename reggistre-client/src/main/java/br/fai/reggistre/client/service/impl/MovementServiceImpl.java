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
import br.fai.reggistre.model.entities.PessoaFisica;

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
	public Long create(Movimentacao entity) {

		Long id = Long.valueOf(0);

		String endPoint = "http://localhost:8082/api/v1/movement/create";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Movimentacao> requestEntity = new HttpEntity<Movimentacao>(entity);
			ResponseEntity<String> requestResponse = restTemplate.exchange(endPoint, HttpMethod.POST,
					requestEntity, String.class);

			String response = requestResponse.getBody();
			id = Long.parseLong(response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return id;
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
		boolean response = false;
		
		String endPoint = "http://localhost:8082/api/v1/movement/update";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Movimentacao> requestEntity = new HttpEntity<Movimentacao>(entity);
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endPoint, HttpMethod.PUT,
					requestEntity, Boolean.class);

			 response = requestResponse.getBody();
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	@Override
	public boolean deleteById(Long id) {
		
		boolean response = false;

		String endPoint = "http://localhost:8082/api/v1/movement/delete/" + id;

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			ResponseEntity<Boolean> resquestResponse = restTemplate.exchange(endPoint, HttpMethod.DELETE,
					requestEntity, Boolean.class);

			response = resquestResponse.getBody();

		} catch (Exception e) {
			System.out.println("user service impl delete by id");
			System.out.println(e.getMessage());
		}

		return response;
	}

}
