package site.gabriellima.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import site.gabriellima.api.model.domain.Credenciais;
import site.gabriellima.api.model.domain.ErroMensagem;
import site.gabriellima.api.model.domain.Usuario;
import site.gabriellima.api.service.UsuarioService;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	private UsuarioService service = new UsuarioService();

	@POST
	public Response save(Usuario usuario) {
		service.salvar(usuario);
		return Response.status(Status.CREATED).build();
	}
	
	@POST()
	@Path("/autenticacao")
	public Response auth(Credenciais credenciais) {
		Usuario usuario = service.autenticacao(credenciais);
		
		if (usuario != null) {
			return Response.ok().entity(usuario).build();
		}
		
		return Response.status(403).entity(new ErroMensagem("Usuário com senha inválida", 403)).build();
	}
}
