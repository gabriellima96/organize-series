package site.gabriellima.api.model.repository;

import site.gabriellima.api.model.domain.Usuario;

public interface UsuarioRepository extends CRUDRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
}
