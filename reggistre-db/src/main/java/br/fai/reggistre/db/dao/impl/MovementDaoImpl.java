package br.fai.reggistre.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import br.fai.reggistre.db.connection.ConnectionFactory;
import br.fai.reggistre.db.dao.MovementDao;
import br.fai.reggistre.model.entities.Movimentacao;

@Repository
public class MovementDaoImpl implements MovementDao{

	@Override
	public List<Movimentacao> readAll() {
		
		List<Movimentacao> movimentacaoList = new ArrayList<Movimentacao>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM movimentacao ; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet  = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Movimentacao movimentacao = new Movimentacao();
				movimentacao.setId(resultSet.getLong("id"));
				movimentacao.setNome(resultSet.getString("nome"));
				movimentacao.setCategoriaId(resultSet.getLong("categoria_id"));
				movimentacao.setData(resultSet.getDate("data"));
				movimentacao.setDescricao(resultSet.getString("descricao"));
				movimentacao.setPessoaFisicaId(resultSet.getLong("pessoa_fisica_id"));
				movimentacao.setTipoMovimentacao(resultSet.getString("tipo_movimentacao"));
				movimentacao.setValor(resultSet.getDouble("valor"));
				
				movimentacaoList.add(movimentacao);
				
				
			}
			
		} catch (Exception e) {
			System.out.println("Erro no movimentDaoImpl pacote DB readAll");
		}finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		
		return movimentacaoList;
	}
	
	@Override
	public boolean create(Movimentacao entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO movimentacao (nome, categoria_id, data, descricao, pessoa_fisica_id, tipo_movimentacao, valor) ";
		sql+= " VALUES (?, ?, ?, ?, ?, ?, ?); ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setLong(2, entity.getCategoriaId());
			preparedStatement.setDate(3, entity.getData());
			preparedStatement.setString(4, entity.getDescricao());
			preparedStatement.setLong(5, entity.getPessoaFisicaId());
			preparedStatement.setString(6, entity.getTipoMovimentacao());
			preparedStatement.setDouble(7, entity.getValor());
			
			preparedStatement.execute();
			
			connection.commit();
			return true;
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("MovementDaoImpl pacote DB create");
				e1.printStackTrace();
			}
			return false;
		}finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
	}

	@Override
	public Movimentacao readById(Long id) {
		
		Movimentacao movimentacao = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM movimentacao WHERE id = ? ;";
		
		try {
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				movimentacao = new Movimentacao();
				movimentacao.setId(resultSet.getLong("id"));
				movimentacao.setNome(resultSet.getString("nome"));
				movimentacao.setCategoriaId(resultSet.getLong("categoria_id"));
				movimentacao.setData(resultSet.getDate("data"));
				movimentacao.setDescricao(resultSet.getString("descricao"));
				movimentacao.setPessoaFisicaId(resultSet.getLong("pessoa_fisica_id"));
				movimentacao.setTipoMovimentacao(resultSet.getString("tipo_movimentacao"));
				movimentacao.setValor(resultSet.getDouble("valor"));
			}
			
		} catch (Exception e) {
			System.out.println("MovementDaoImpl pacote db readById");
		}finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return movimentacao;
	}

	@Override
	public boolean update(Movimentacao entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "UPDATE movimentacao SET nome = ?, categoria_id = ?, data = ?, descricao = ?, tipo_movimentacao = ?, valor = ? ";
		sql += " WHERE id = ? ; ";
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setLong(2, entity.getCategoriaId());
			preparedStatement.setDate(3, entity.getData());
			preparedStatement.setString(4, entity.getDescricao());
			preparedStatement.setString(5, entity.getTipoMovimentacao());
			preparedStatement.setDouble(6, entity.getValor());
			preparedStatement.setLong(7, entity.getId());
			
			preparedStatement.execute();
			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("MovementDaoImpl pacote DB update");
				e1.printStackTrace();
			}
			return false;
			
		}finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
			
	}

	@Override
	public boolean deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " DELETE FROM movimentacao WHERE id = ? ; ";
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			preparedStatement.execute();
			connection.commit();
			return true;
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("movementDaoImpl pacote DB deleteById");
				e1.printStackTrace();
			}
			return false;
		}finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		
		
	}

}
