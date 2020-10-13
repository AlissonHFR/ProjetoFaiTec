package br.fai.reggistre.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.reggistre.client.service.CategoryService;
import br.fai.reggistre.model.entities.Categoria;



@Service
public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Categoria> readAll() {
		List<Categoria> response = null;

		String endPoint = "http://localhost:8082/api/v1/category/read-all";

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<String> requestEntity = new HttpEntity<>("");

			ResponseEntity<Categoria[]> requestResponse = restTemplate.exchange(endPoint, HttpMethod.GET, requestEntity,
					Categoria[].class);

			Categoria[] categoriaList = requestResponse.getBody();
			response = Arrays.asList(categoriaList);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(Categoria entity) {
		Long id = Long.valueOf(0);

		String endPoint = "http://localhost:8082/api/v1/category/create";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Categoria> requestEntity = new HttpEntity<Categoria>(entity);
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
	public Categoria readById(Long id) {
		Categoria response = null;

		String endPoint = "http://localhost:8082/api/v1/category/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Categoria> resquestResponse = restTemplate.exchange(endPoint, HttpMethod.GET, requestEntity,
					Categoria.class);

			response = resquestResponse.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Categoria entity) {
		boolean response = false;
		
		String endPoint = "http://localhost:8082/api/v1/category/update";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Categoria> requestEntity = new HttpEntity<Categoria>(entity);
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

		String endPoint = "http://localhost:8082/api/v1/category/delete/" + id;

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
