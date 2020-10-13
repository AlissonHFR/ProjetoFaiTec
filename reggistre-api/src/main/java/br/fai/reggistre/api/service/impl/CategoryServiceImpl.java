package br.fai.reggistre.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.reggistre.api.service.CategoryService;
import br.fai.reggistre.db.dao.CategoryDao;
import br.fai.reggistre.model.entities.Categoria;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Categoria> readAll() {
		
		return categoryDao.readAll();
	}

	@Override
	public Long create(Categoria entity) {
		
		return categoryDao.create(entity);
	}

	@Override
	public Categoria readById(Long id) {
		
		return categoryDao.readById(id);
	}

	@Override
	public boolean update(Categoria entity) {
		
		return categoryDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return categoryDao.deleteById(id);
	}

}
