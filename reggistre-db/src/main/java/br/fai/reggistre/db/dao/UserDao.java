package br.fai.reggistre.db.dao;

import java.util.List;

import br.fai.reggistre.model.entities.PessoaFisica;

public interface UserDao {
	
	List<PessoaFisica> readAll();
	
	boolean create(PessoaFisica entity);
		
	PessoaFisica readById(Long id);
	
	boolean update(PessoaFisica entity);
	
	boolean deleteById(Long id);

}
