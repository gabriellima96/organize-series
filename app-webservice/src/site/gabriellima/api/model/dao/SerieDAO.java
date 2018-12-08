package site.gabriellima.api.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import site.gabriellima.api.exception.DAOException;
import site.gabriellima.api.exception.enums.ErroCodigo;
import site.gabriellima.api.model.domain.Genero;
import site.gabriellima.api.model.domain.Serie;
import site.gabriellima.api.model.repository.SerieRepository;

public class SerieDAO implements SerieRepository {

	private Database db = Database.getInstance();
	private GeneroDAO generoDAO = new GeneroDAO();
	
	@Override
	public void save(Serie obj) {
		if (!serieIsValid(obj)) {
            throw new DAOException("Serie com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		try {
			Connection con = db.getConnection();
			
			String sql = "INSERT INTO serie (usuario_id, nome, detalhes, ano_lancamento) VALUES (?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, obj.getUsuarioId());
			stm.setString(2, obj.getNome());
			stm.setString(3, obj.getDetalhes());
			stm.setInt(4, obj.getAnoLancamento());

			stm.execute();

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao salvar serie no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

	@Override
	public void update(Serie obj) {
		if (obj.getId() <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		if (!serieIsValid(obj)) {
            throw new DAOException("Serie com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		if (!obj.getGeneros().isEmpty()) {
			generoDAO.removeAllBySerie_id(obj.getId());
			generoDAO.saveAllBySerie_id(obj.getId(), obj.getGeneros());
		}
		
		Boolean resultado = false;
		try {
			Connection con = db.getConnection();
			
			String sql = "UPDATE serie SET nome = ?, detalhes = ?, ano_lancamento = ? WHERE id = ? and usuario_id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getNome());
			stm.setString(2, obj.getDetalhes());
			stm.setInt(3, obj.getAnoLancamento());
			stm.setInt(4, obj.getId());
			stm.setInt(5, obj.getUsuarioId());
			
			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao atualizar serie no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("serie informado para atualização não existe: id "+ obj.getId(), ErroCodigo.NOT_FOUND.getCodigo());
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
			String sql = "DELETE FROM serie WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);

			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();

		} catch (Exception ex) {
			throw new DAOException("Erro ao remover serie do banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("serie informado para remoção não existe: id "+ id, ErroCodigo.NOT_FOUND.getCodigo());
		}
		
	}

	@Override
	public List<Serie> findAll() {
		return null;
	}

	@Override
	public Serie findById(Integer id) {
		
		if (id <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Serie serie = null;
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM serie WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				serie = new Serie(rs.getInt("id"), rs.getInt("usuario_id"), rs.getString("nome"), rs.getString("detalhes"), rs.getInt("ano_lancamento"));
				Set<Genero> generos = generoDAO.findAllBySerie_id(serie.getId());
				serie.setGeneros(generos);
			}

			rs.close();
			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao buscar serie por id no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (serie == null) {
            throw new DAOException("Serie de id " + id + " nao existe.", ErroCodigo.NOT_FOUND.getCodigo());
        }
		
		return serie;

	}
	
	private Boolean serieIsValid(Serie obj) {
		try {
            if (obj.getNome().isEmpty() || obj.getAnoLancamento() == null)
                return false;
        } catch (NullPointerException ex) {
            throw new DAOException("Serie com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
         
        return true;
	}

	@Override
	public List<Serie> findAll(Integer userId) {
		List<Serie> series = new ArrayList<>();

		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM serie "
					+ "INNER JOIN usuario "
					+ "ON usuario.id = serie.usuario_id "
					+ "WHERE usuario.id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, userId);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Serie serie = new Serie(rs.getInt("id"), rs.getInt("usuario_id"), rs.getString("nome"), rs.getString("detalhes"), rs.getInt("ano_lancamento"));
				Set<Genero> generos = generoDAO.findAllBySerie_id(serie.getId());
				serie.setGeneros(generos);
				
				series.add(serie);
			}

			rs.close();
			stm.close();
			con.close();

			return series;
		} catch (Exception ex) {
			throw new DAOException("Erro ao recuperar todos as series do usuário no banco: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}

	}

}
