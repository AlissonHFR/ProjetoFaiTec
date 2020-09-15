package br.fai.reggistre.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.reggistre.client.service.UserService;
import br.fai.reggistre.model.entities.PessoaFisica;


@Service
public class UserServiceImpl implements UserService {
	
	

	@Override
	public List<PessoaFisica> readAll() {
		
		List<PessoaFisica> response = null;
		
		String endPonit = "http://localhost:8082/api/v1/user/read-all";
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			
			ResponseEntity<PessoaFisica[]> requestResponse = restTemplate.exchange(endPonit, HttpMethod.GET,requestEntity, PessoaFisica[].class);
			
			PessoaFisica[] pFisicasList = requestResponse.getBody();
			response = Arrays.asList(pFisicasList);
			
		} catch (Exception e) {
			System.out.println("user serviceimple read all");
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	@Override
	public boolean create(PessoaFisica entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PessoaFisica readById(Long id) {
		
		PessoaFisica response = null;
		
		String endPoint = "http://localhost:8082/api/v1/user/read-by-id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			ResponseEntity<PessoaFisica> resquestResponse = restTemplate.exchange(endPoint, HttpMethod.GET, requestEntity, PessoaFisica.class);
			
			response = resquestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println("user service impl read by id");
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	@Override
	public boolean update(PessoaFisica entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
