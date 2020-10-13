package br.fai.reggistre.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.reggistre.db.connection.ConnectionFactory;
import br.fai.reggistre.db.dao.CategoryDao;
import br.fai.reggistre.model.entities.Categoria;

@Repository
public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Categoria> readAll() {
		
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM categoria ; ";
		
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(resultSet.getLong("id"));
				categoria.setNome(resultSet.getString("nome"));
				categoria.setPessoaFisicaId(resultSet.getLong("pessoa_fisica_id"));
				categoria.setTipo(resultSet.getString("tipo"));
				categoria.setIcone(resultSet.getInt("icone"));
				
				categoriaList.add(categoria);
			}
			
		} catch (Exception e) {
			System.out.println("CategoryDaoImpl pacote DB readAll");
		}finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return categoriaList;
	}

	@Override
	public Long create(Categoria entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long id = Long.valueOf(0);
		
		String sql = "INSERT INTO categoria (nome, pessoa_fisica_id, tipo, icone) ";
		sql += " VALUES (?, ?, ?, ?); " ; 
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement  = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setLong(2, entity.getPessoaFisicaId());
			preparedStatement.setString(3, entity.getTipo());
			preparedStatement.setInt(4, entity.getIcone());
			
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				id =resultSet.getLong(1);
				
			}
			
			connection.commit();
			return id;
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("CategotyDaoImpl pacote DB create");
				e1.printStackTrace();
			}
			return id;
		}finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
	}

	@Override
	public Categoria readById(Long id) {
		Categoria categoria = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM categoria WHERE id = ? ;";
		
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				categoria = new Categoria();
				categoria.setId(resultSet.getLong("id"));
				categoria.setNome(resultSet.getString("nome"));
				categoria.setPessoaFisicaId(resultSet.getLong("pessoa_fisica_id"));
				categoria.setTipo(resultSet.getString("tipo"));
				categoria.setIcone(resultSet.getInt("icone"));
				
			}
			
		} catch (Exception e) {
			System.out.println("CategoryDaoImpl pacote DB readById");
		}finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		
		return categoria;
	}

	@Override
	public boolean update(Categoria entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "UPDATE categoria SET nome = ?, tipo = ?, icone = ? ";
		sql += " WHERE id = ? ; ";
		
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getTipo());
			preparedStatement.setInt(3, entity.getIcone());
			preparedStatement.setLong(4, entity.getId());
			
			preparedStatement.execute();
			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("CategoryDaoImpl pacote DB update");
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
		
		String sql = "DELETE FROM categoria  WHERE id = ? ; ";
		
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
				System.out.println("CategoryDaoImpl pacote DB delete");
				e1.printStackTrace();
			}
			return false;
		}finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
	}

}
