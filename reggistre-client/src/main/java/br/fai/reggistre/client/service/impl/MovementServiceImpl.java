package br.fai.reggistre.client.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Movimentacao;

@Service
public class MovementServiceImpl implements MovementService{

	@Override
	public List<Movimentacao> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Movimentacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movimentacao readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Movimentacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
