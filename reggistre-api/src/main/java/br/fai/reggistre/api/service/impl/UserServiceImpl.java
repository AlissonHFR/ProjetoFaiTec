package br.fai.reggistre.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.reggistre.api.service.UserService;
import br.fai.reggistre.db.dao.UserDao;
import br.fai.reggistre.model.entities.PessoaFisica;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<PessoaFisica> readAll() {
		
		return userDao.readAll();
	}

	@Override
	public Long create(PessoaFisica entity) {
		
		return userDao.create(entity);
	}

	@Override
	public PessoaFisica readById(Long id) {
		
		return userDao.readById(id);
	}

	@Override
	public boolean update(PessoaFisica entity) {
		
		return userDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return userDao.deleteById(id);
	}

	@Override
	public PessoaFisica readByLogin(String nomeUsuario, String senha) {
		
		return userDao.readByLogin(nomeUsuario, senha);
	}

}
