package br.fai.reggistre.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection = null;

	private static final String url = "jdbc:postgresql://localhost:5432/reggistre";
	private static final String user = "postgres";
	private static final String password = "123";
//	private static final String password = "1234"; //Gabi
	//private static final String password = "postgres"; //Alisson

	public static Connection getConnection() {

		try {

			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	private static void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		closeConnection(connection);
	}
	
	public static void close( PreparedStatement preparedStatement, Connection connection) {
		
		closePreparedStatement(preparedStatement);
		closeConnection(connection);
	}

}
