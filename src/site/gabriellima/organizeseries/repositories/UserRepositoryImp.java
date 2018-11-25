package site.gabriellima.organizeseries.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import site.gabriellima.organizeseries.configs.Database;
import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.exceptions.PersistException;

public class UserRepositoryImp implements UserRepository {

	private Database db = Database.getInstance();

	@Override
	public Boolean save(User obj) throws PersistException {
		boolean result = false;

		try {
			Connection con = db.getConnection();

			String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			stm.setString(1, obj.getName());
			stm.setString(2, obj.getEmail());
			stm.setString(3, obj.getPassword());

			result = stm.execute();

			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public User update(User obj) throws PersistException {
		return null;
	}

	@Override
	public User remove(Integer id) throws PersistException {
		return null;
	}

	@Override
	public List<User> findAll() throws PersistException {
		return null;
	}

	@Override
	public User findById(Integer id) throws PersistException {
		return null;
	}

	@Override
	public User findOneByEmail(String email) throws PersistException {
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM user WHERE email = '"+ email+ "'";
			PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			User user = null;
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
			}

			rs.close();
			stm.close();
			con.close();

			return user;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

}