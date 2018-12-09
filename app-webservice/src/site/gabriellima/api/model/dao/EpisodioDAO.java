package site.gabriellima.api.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import site.gabriellima.api.exception.DAOException;
import site.gabriellima.api.exception.enums.ErroCodigo;
import site.gabriellima.api.model.domain.Episodio;
import site.gabriellima.api.model.repository.EpisodioRepository;

public class EpisodioDAO implements EpisodioRepository {

	private Database db = Database.getInstance();

	@Override
	public void save(Episodio obj) {
		if (!episodioIsValid(obj)) {
			throw new DAOException("Episodio com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
		}

		try {
			Connection con = db.getConnection();

			String sql = "INSERT INTO episodio (serie_id, temporada, titulo, detalhes, assisti) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, obj.getSerieId());
			stm.setInt(2, obj.getTemporada());
			stm.setString(3, obj.getTitulo());
			stm.setString(4, obj.getDetalhes());
			stm.setBoolean(5, obj.getAssisti());

			stm.execute();

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao salvar episodio no banco de dados: " + ex.getMessage(),
					ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

	@Override
	public void update(Episodio obj) {
		if (obj.getId() <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		if (!episodioIsValid(obj)) {
            throw new DAOException("Episodio com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Boolean resultado = false;
		try {
			Connection con = db.getConnection();
			
			String sql = "UPDATE episodio SET temporada = ?, titulo = ?, detalhes = ?, assisti = ? WHERE id = ? and serie_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, obj.getTemporada());
			stm.setString(2, obj.getTitulo());
			stm.setString(3, obj.getDetalhes());
			stm.setBoolean(4, obj.getAssisti());
			stm.setInt(5, obj.getId());
			stm.setInt(6, obj.getSerieId());
			
			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao atualizar episodio no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("Episodio informado para atualização não existe: id "+ obj.getId(), ErroCodigo.NOT_FOUND.getCodigo());
		}
	}

	@Override
	public void remove(Integer id) {
		if (id <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }

        Boolean resultado = false;
		try {
			Connection con = db.getConnection();

			// Sem verificação do Usuário
			String sql = "DELETE FROM episodio WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);

			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();

		} catch (Exception ex) {
			throw new DAOException("Erro ao remover episodio do banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("Episodio informado para remoção não existe: id "+ id, ErroCodigo.NOT_FOUND.getCodigo());
		}
	}

	@Override
	public List<Episodio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Episodio findById(Integer id) {
		if (id <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Episodio episodio = null;
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM episodio WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				episodio = new Episodio(rs.getInt("id"), rs.getInt("serie_id"), rs.getString("titulo"), rs.getString("detalhes"), rs.getBoolean("assisti"), rs.getInt("temporada"));
			}

			rs.close();
			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao buscar episodio por id no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (episodio == null) {
            throw new DAOException("Episodio de id " + id + " nao existe.", ErroCodigo.NOT_FOUND.getCodigo());
        }
		
		return episodio;
	}

	@Override
	public List<Episodio> findAll(Integer serieId) {
		List<Episodio> episodios = new ArrayList<>();

		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM episodio WHERE serie_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, serieId);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				episodios.add(new Episodio(rs.getInt("id"), rs.getInt("serie_id"), rs.getString("titulo"), rs.getString("detalhes"), rs.getBoolean("assisti"), rs.getInt("temporada")));
			}

			rs.close();
			stm.close();
			con.close();

			return episodios;
		} catch (Exception ex) {
			throw new DAOException("Erro ao recuperar todos os episodios do usuário no banco: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}

	}
	
	private Boolean episodioIsValid(Episodio obj) {
		try {
			if (obj.getTitulo().isEmpty() || obj.getTemporada() == null || obj.getSerieId() == null)
				return false;
		} catch (NullPointerException ex) {
			throw new DAOException("Episodio com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
		}

		return true;
	}
}
