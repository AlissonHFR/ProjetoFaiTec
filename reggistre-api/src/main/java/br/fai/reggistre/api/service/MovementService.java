package br.fai.reggistre.api.service;

import java.util.List;

import br.fai.reggistre.model.entities.Movimentacao;

public interface MovementService {
	List<Movimentacao> readAll();
	
	Long create(Movimentacao entity);
		
	Movimentacao readById(Long id);
	
	boolean update(Movimentacao entity);
	
	boolean deleteById(Long id);
}
