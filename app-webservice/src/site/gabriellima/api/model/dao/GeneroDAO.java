package site.gabriellima.api.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import site.gabriellima.api.exception.DAOException;
import site.gabriellima.api.exception.enums.ErroCodigo;
import site.gabriellima.api.model.domain.Genero;
import site.gabriellima.api.model.repository.GeneroRepository;

public class GeneroDAO implements GeneroRepository {
	
	private Database db = Database.getInstance();

	@Override
	public void save(Genero obj) {
		
		if (!generoIsValid(obj)) {
            throw new DAOException("Gênero com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		try {
			Connection con = db.getConnection();
			
			String sql = "INSERT INTO genero (nome) VALUES (?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getNome());

			stm.execute();

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao salvar genero no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

	@Override
	public void update(Genero obj) {
		
		if (obj.getId() <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		if (!generoIsValid(obj)) {
            throw new DAOException("Gênero com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Boolean resultado = false;
		try {
			Connection con = db.getConnection();
			
			String sql = "UPDATE genero SET nome = ? WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getNome());
			stm.setInt(2, obj.getId());
			
			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao atualizar genero no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("gênero informado para atualização não existe: id "+ obj.getId(), ErroCodigo.NOT_FOUND.getCodigo());
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

			String sql = "DELETE FROM genero WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);

			resultado = stm.executeUpdate() == 1 ? true : false;

			stm.close();
			con.close();

		} catch (Exception ex) {
			throw new DAOException("Erro ao remover gênero do banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (!resultado) {
			throw new DAOException("gênero informado para remoção não existe: id "+ id, ErroCodigo.NOT_FOUND.getCodigo());
		}
	}

	@Override
	public List<Genero> findAll() {
		List<Genero> generos = new ArrayList<>();

		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM genero";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				generos.add(new Genero(rs.getInt("id"), rs.getString("nome")));
			}

			rs.close();
			stm.close();
			con.close();

			return generos;
		} catch (Exception ex) {
			throw new DAOException("Erro ao recuperar todos os gêneros do banco: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

	@Override
	public Genero findById(Integer id) {
		
		if (id <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Genero genero = null;
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM genero WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				genero = new Genero(rs.getInt("id"), rs.getString("nome"));
			}

			rs.close();
			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao buscar genêro por id no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (genero == null) {
            throw new DAOException("Gênero de id " + id + " nao existe.", ErroCodigo.NOT_FOUND.getCodigo());
        }
		
		return genero;
	}
	
	private Boolean generoIsValid(Genero obj) {
		try {
            if (obj.getNome().isEmpty())
                return false;
        } catch (NullPointerException ex) {
            throw new DAOException("Gênero com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
         
        return true;
	}
}
