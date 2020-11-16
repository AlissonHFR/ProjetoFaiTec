package br.fai.reggistre.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResizableByteArrayOutputStream;

import br.fai.reggistre.db.connection.ConnectionFactory;
import br.fai.reggistre.db.dao.UserDao;
import br.fai.reggistre.model.entities.PessoaFisica;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public List<PessoaFisica> readAll() {

		List<PessoaFisica> pFisicaList = new ArrayList<PessoaFisica>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa_fisica;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				PessoaFisica pFisica = new PessoaFisica();
				pFisica.setId(resultSet.getLong("id"));
				pFisica.setNomeUsuario(resultSet.getString("nome_usuario"));
				pFisica.setNomeCompleto(resultSet.getString("nome_completo"));
				pFisica.setEmail(resultSet.getString("email"));
				pFisica.setEmailAlternativo(resultSet.getString("email_alternativo"));
				pFisica.setSenha(resultSet.getString("senha"));

				pFisicaList.add(pFisica);

			}

		} catch (Exception e) {
			System.out.println("userDao DB Read all");

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return pFisicaList;

	}

	@Override
	public Long create(PessoaFisica entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO pessoa_fisica (nome_usuario, nome_completo, email, email_alternativo, senha) ";
		sql += " VALUES (?, ?, ?, ?, ?); ";

		Long id = Long.valueOf(0);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNomeUsuario());
			preparedStatement.setString(2, entity.getNomeCompleto());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setString(4, entity.getEmailAlternativo());
			preparedStatement.setString(5, entity.getSenha());

			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}

			connection.commit();
			return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("userDao  DB create");
				e1.printStackTrace();
			}
			return id;

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public PessoaFisica readById(Long id) {

		PessoaFisica pFisica = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM pessoa_fisica WHERE id = ?;";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pFisica = new PessoaFisica();
				pFisica.setId(resultSet.getLong("id"));
				pFisica.setNomeUsuario(resultSet.getString("nome_usuario"));
				pFisica.setNomeCompleto(resultSet.getString("nome_completo"));
				pFisica.setEmail(resultSet.getString("email"));
				pFisica.setEmailAlternativo(resultSet.getString("email_alternativo"));
				pFisica.setSenha(resultSet.getString("senha"));

			}

		} catch (Exception e) {
			System.out.println("userDao  DB Read by id");
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return pFisica;
	}

	@Override
	public boolean update(PessoaFisica entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE pessoa_fisica SET nome_completo = ?, email = ?, email_alternativo = ?, senha = ? ";
		sql += " WHERE id = ? ; ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNomeCompleto());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setString(3, entity.getEmailAlternativo());
			preparedStatement.setString(4, entity.getSenha());
			preparedStatement.setLong(5, entity.getId());

			preparedStatement.execute();
			connection.commit();

			return true;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("userDao  DB update");
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM pessoa_fisica WHERE id = ? ;";

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
				System.out.println("userDao  DB delete");
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

	// Parte do login
	@Override
	public PessoaFisica readByLogin(String nomeUsuario, String senha) {

		PessoaFisica pFisica = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM pessoa_fisica WHERE nome_usuario = ? and senha = ?;";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nomeUsuario);
			preparedStatement.setString(2, senha);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pFisica = new PessoaFisica();
				pFisica.setId(resultSet.getLong("id"));
				pFisica.setNomeUsuario(resultSet.getString("nome_usuario"));
				pFisica.setNomeCompleto(resultSet.getString("nome_completo"));
				pFisica.setEmail(resultSet.getString("email"));
				pFisica.setEmailAlternativo(resultSet.getString("email_alternativo"));
				pFisica.setSenha(resultSet.getString("senha"));
			}

		} catch (Exception e) {
			System.out.println("userDao  DB Read by Login");
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return pFisica;
	}

}
