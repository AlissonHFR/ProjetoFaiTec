package br.fai.reggistre.client.service;

import java.util.List;

import br.fai.reggistre.model.entities.Movimentacao;

public interface MovementService {

	
	List<Movimentacao> readAll();
	
	boolean create(Movimentacao entity);
		
	Movimentacao readById(Long id);
	
	boolean update(Movimentacao entity);
	
	boolean deleteById(Long id);
}
