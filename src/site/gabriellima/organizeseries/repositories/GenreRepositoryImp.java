package site.gabriellima.organizeseries.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import site.gabriellima.organizeseries.configs.Database;
import site.gabriellima.organizeseries.entities.Genre;
import site.gabriellima.organizeseries.exceptions.PersistException;

public class GenreRepositoryImp implements GenreRepository {

	private Database db = Database.getInstance();

	@Override
	public Boolean save(Genre obj) throws PersistException {
		boolean result = false;

		try {
			Connection con = db.getConnection();

			String sql = "INSERT INTO genre (name) VALUES (?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getName());

			result = stm.execute();

			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}

	}

	@Override
	public Genre update(Genre obj) throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean remove(Integer id) throws PersistException {
		boolean result = false;
		try {
			Connection con = db.getConnection();

			String sql = "DELETE FROM genre WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);

			result = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public List<Genre> findAll() throws PersistException {
		List<Genre> genres = new ArrayList<>();

		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM genre";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				genres.add(new Genre(rs.getInt("id"), rs.getString("name")));
			}

			rs.close();
			stm.close();
			con.close();

			return genres;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public Genre findById(Integer id) throws PersistException {
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM genre WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			Genre genre = null;
			while (rs.next()) {
				genre = new Genre(rs.getInt("id"), rs.getString("name"));
			}

			rs.close();
			stm.close();
			con.close();

			return genre;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public Genre findOneByName(String name) throws PersistException {
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM genre WHERE name = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, name);
			ResultSet rs = stm.executeQuery();

			Genre genre = null;
			while (rs.next()) {
				genre = new Genre(rs.getInt("id"), rs.getString("name"));
			}

			rs.close();
			stm.close();
			con.close();

			return genre;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

}
