package site.gabriellima.api.service;

import site.gabriellima.api.model.dao.UsuarioDAO;
import site.gabriellima.api.model.domain.Credenciais;
import site.gabriellima.api.model.domain.Usuario;

public class UsuarioService {

	private UsuarioDAO dao = new UsuarioDAO();

	public void salvar(Usuario obj) {
		dao.save(obj);
	}
	
	public Usuario buscarPorId(Integer id) {
		return dao.findById(id);
	}
	
	public Usuario autenticacao(Credenciais credenciais) {
		
		if (credenciais.getEmail() == null 
				|| credenciais.getEmail().isEmpty() 
				|| credenciais.getSenha() == null 
				|| credenciais.getSenha().isEmpty()) {
			return null;
		}
		
		Usuario usuario = dao.findByEmail(credenciais.getEmail());
		
		if (!usuario.getSenha().equals(credenciais.getSenha()))
			return null;
		
		return usuario;
	}

}
