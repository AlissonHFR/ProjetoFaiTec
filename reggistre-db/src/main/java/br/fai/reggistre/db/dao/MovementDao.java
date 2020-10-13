package br.fai.reggistre.db.dao;

import java.util.List;

import br.fai.reggistre.model.entities.Movimentacao;



public interface MovementDao {
	
    List<Movimentacao> readAll();
	
	Long create(Movimentacao entity);
		
	Movimentacao readById(Long id);
	
	boolean update(Movimentacao entity);
	
	boolean deleteById(Long id);

}
