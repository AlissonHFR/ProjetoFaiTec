package br.fai.reggistre.db.dao;

import java.util.List;

import br.fai.reggistre.model.entities.Categoria;



public interface CategoryDao {

	List<Categoria> readAll();
	
	Long create(Categoria entity);
		
	Categoria readById(Long id);
	
	boolean update(Categoria entity);
	
	boolean deleteById(Long id);
	
}
