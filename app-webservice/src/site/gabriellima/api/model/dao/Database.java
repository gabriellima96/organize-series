package site.gabriellima.api.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import site.gabriellima.api.exception.DAOException;
import site.gabriellima.api.exception.enums.ErroCodigo;

public class Database {

	private static final String DATABASE_DRIVE = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/organizer?useSSL=false";
	private static final String DATABASE_USERNAME = "gabriellima";
	private static final String DATABASE_PASSWORD = "";

	private static Database databaseConnection;

	public static Database getInstance() {
		if (databaseConnection == null)
			databaseConnection = new Database();

		return databaseConnection;
	}

	public Connection getConnection() {
		try {
			Class.forName(DATABASE_DRIVE);
			return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Erro classe do drive não encontrado: " + ex.getMessage(),
					ErroCodigo.SERVER_ERROR.getCodigo());
		} catch (SQLException ex) {
			throw new DAOException("Erro SQL inválido: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

}
