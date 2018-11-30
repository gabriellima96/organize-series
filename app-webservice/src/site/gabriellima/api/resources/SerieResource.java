package site.gabriellima.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import site.gabriellima.api.model.domain.Serie;
import site.gabriellima.api.service.SerieService;

@Path("/{usuarioId}/series")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SerieResource {

	private SerieService service = new SerieService();

	@POST
	public Response save(@PathParam("usuarioId") Integer usuarioId, Serie serie) {
		serie.setUsuarioId(usuarioId);
		service.salvar(serie);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		service.deletar(id);
	}

	@GET
	@Path("{id}")
	public Serie findById(@PathParam("id") Integer id) {
		return service.buscarPorId(id);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("usuarioId") Integer usuarioId, @PathParam("id") Integer id, Serie serie) {
		serie.setUsuarioId(usuarioId);
		serie.setId(id);
		service.atualizar(serie);
	}

	@GET
    public List<Serie> findAll(@PathParam("usuarioId") Integer usuarioId) {       
        return service.buscarTodos(usuarioId);
    }
	
}
