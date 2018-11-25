package site.gabriellima.organizeseries.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static final String DATABASE_DRIVE = "com.mysql.jdbc.Driver";
	private static final String DATABASE_NAME = "organizer";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"+ DATABASE_NAME+ "?useSSL=false";
	private static final String DATABASE_USERNAME = "gabriellima";
	private static final String DATABASE_PASSWORD = "";

	private static Database databaseConnection;

	public static Database getInstance() {
		if (databaseConnection == null)
			databaseConnection = new Database();

		return databaseConnection;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DATABASE_DRIVE);
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	}
}
