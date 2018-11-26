package site.gabriellima.organizeseries.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import site.gabriellima.organizeseries.configs.Database;
import site.gabriellima.organizeseries.entities.Genre;
import site.gabriellima.organizeseries.entities.Series;
import site.gabriellima.organizeseries.entities.dto.SeriesDTO;
import site.gabriellima.organizeseries.exceptions.PersistException;

public class SeriesRepositoryImp implements SeriesRepository {

	private Database db = Database.getInstance();
	private GenreRepositoryImp genreRepository = new GenreRepositoryImp();

	@Override
	public Boolean save(Series obj) throws PersistException {
		boolean result = false;

		try {
			Connection con = db.getConnection();

			String sql = "INSERT INTO series (user_id, name, description, releaseDate) VALUES (?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, obj.getUser().getId());
			stm.setString(2, obj.getName());
			stm.setString(3, obj.getDescription());
			stm.setDate(4, (Date) obj.getReleaseDate());

			result = stm.execute();

			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}

	}

	@Override
	public Boolean update(Series obj) throws PersistException {
		boolean result = false;

		try {
			Connection con = db.getConnection();

			String sql = "UPDATE series SET name = ?, description = ?, releaseDate = ? WHERE id = ? and user_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getName());
			stm.setString(2, obj.getDescription());
			stm.setDate(3, (Date) obj.getReleaseDate());
			stm.setInt(4, obj.getId());
			stm.setInt(5, obj.getUser().getId());

			result = stm.execute();
			
			sql = "DELETE FROM series_genre WHERE series_id = "+ obj.getId();
			stm = con.prepareStatement(sql);
			result = stm.execute();
			
			sql = series_genres(obj.getGenres(), obj.getId());

			stm = con.prepareStatement(sql);
			result = stm.execute();
			
			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean remove(Integer id) throws PersistException {
		return null;
	}

	@Override
	public List<SeriesDTO> findAll(Integer userId) throws PersistException {
		List<SeriesDTO> series = new ArrayList<>();

		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM series INNER JOIN user ON user.id = series.user_id WHERE user.id = ?";

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, userId);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				SeriesDTO seriesObj = new SeriesDTO(rs.getInt("series.id"), rs.getString("series.name"),
						rs.getString("series.description"), rs.getDate("series.releaseDate"));
				Set<Genre> genres = genreRepository.findAllBySerie_id(seriesObj.getId());
				seriesObj.setGenres(genres);
				series.add(seriesObj);
			}

			rs.close();
			stm.close();
			con.close();

			return series;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public Series findById(Integer id) throws PersistException {
		return null;
	}

	@Override
	public List<Series> findAll() throws PersistException {
		return null;
	}

	@Override
	public SeriesDTO findByIdAndUserId(Integer id, Integer userId) throws PersistException {
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM series INNER JOIN user ON user.id = series.user_id WHERE series.id = ? and user_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			stm.setInt(2, userId);
			ResultSet rs = stm.executeQuery();

			SeriesDTO series = null;
			while (rs.next()) {
				series = new SeriesDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getDate("releaseDate"));
				Set<Genre> genres = genreRepository.findAllBySerie_id(series.getId());
				series.setGenres(genres);
			}

			rs.close();
			stm.close();
			con.close();

			return series;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean remove(Integer id, Integer userId) throws PersistException {
		boolean result = false;
		try {
			Connection con = db.getConnection();

			String sql = "DELETE FROM series WHERE id = ? and user_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			stm.setInt(2, userId);

			result = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();

			return result;
		} catch (Exception e) {
			throw new PersistException(e.getMessage(), e);
		}
	}

	public String series_genres(Set<Genre> genres, Integer seriesId) {
		String sql = "INSERT INTO series_genre (genre_id, series_id) values ";
		for (Genre genre : genres) {
			sql += "(" + genre.getId() + "," + seriesId + "),";
		}
		
		sql = sql.substring(0, sql.length() - 1);
		return sql;
	}

}