package br.fai.reggistre.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.reggistre.api.service.MovementService;
import br.fai.reggistre.db.dao.MovementDao;
import br.fai.reggistre.model.entities.Movimentacao;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	private MovementDao movementDao;
	
	
	@Override
	public List<Movimentacao> readAll() {
		
		return movementDao.readAll();
	}

	@Override
	public Long create(Movimentacao entity) {
		
		return movementDao.create(entity);
	}

	@Override
	public Movimentacao readById(Long id) {
		
		return movementDao.readById(id);
	}

	@Override
	public boolean update(Movimentacao entity) {
		
		return movementDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return movementDao.deleteById(id);
	}

}
