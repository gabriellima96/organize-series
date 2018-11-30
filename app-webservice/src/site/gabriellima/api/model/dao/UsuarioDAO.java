package site.gabriellima.api.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import site.gabriellima.api.exception.DAOException;
import site.gabriellima.api.exception.enums.ErroCodigo;
import site.gabriellima.api.model.domain.Usuario;
import site.gabriellima.api.model.repository.UsuarioRepository;

public class UsuarioDAO implements UsuarioRepository {

	private Database db = Database.getInstance();
	
	@Override
	public void save(Usuario obj) {
		
		if (!usuarioIsValid(obj)) {
            throw new DAOException("Usuário com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		try {
			Connection con = db.getConnection();
			
			String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getNome());
			stm.setString(2, obj.getEmail());
			stm.setString(3, obj.getSenha());

			stm.execute();

			stm.close();
			con.close();
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DAOException("Usuário com o email informado já existe: "+ obj.getEmail(), ErroCodigo.BAD_REQUEST.getCodigo());
		} catch (Exception ex) {
			throw new DAOException("Erro ao salvar usuário no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
	}

	@Override
	public void update(Usuario obj) {
		
	}

	@Override
	public void remove(Integer id) {
		
	}

	@Override
	public List<Usuario> findAll() {
		return null;
	}

	@Override
	public Usuario findById(Integer id) {
		
		if (id <= 0) {
            throw new DAOException("O id precisa ser maior do que 0.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
		
		Usuario usuario = null;
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM usuario WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
			}

			rs.close();
			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao buscar genêro por id no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (usuario == null) {
            throw new DAOException("Usuário de id " + id + " nao existe.", ErroCodigo.NOT_FOUND.getCodigo());
        }
		
		return usuario;
	}
	
	private boolean usuarioIsValid(Usuario obj) {
		try {
            if (obj.getNome().isEmpty() || obj.getEmail().isEmpty() || obj.getSenha().isEmpty())
                return false;
        } catch (NullPointerException ex) {
            throw new DAOException("Usuário com dados incompletos.", ErroCodigo.BAD_REQUEST.getCodigo());
        }
         
        return true;
	}

	@Override
	public Usuario findByEmail(String email) {
		
		Usuario usuario = null;
		try {
			Connection con = db.getConnection();

			String sql = "SELECT * FROM usuario WHERE email = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
			}

			rs.close();
			stm.close();
			con.close();
		} catch (Exception ex) {
			throw new DAOException("Erro ao buscar genêro por id no banco de dados: " + ex.getMessage(), ErroCodigo.SERVER_ERROR.getCodigo());
		}
		
		if (usuario == null) {
            throw new DAOException("Usuário com o email nao existe.", ErroCodigo.NOT_FOUND.getCodigo());
        }
		
		return usuario;

	}

}
