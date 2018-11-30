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

import site.gabriellima.api.model.domain.Genero;
import site.gabriellima.api.service.GeneroService;

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GeneroResource {

	private GeneroService service = new GeneroService();

	@POST
	public Response save(Genero genero) {
		service.salvar(genero);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		service.deletar(id);
	}

	@GET
	@Path("{id}")
	public Genero findById(@PathParam("id") Integer id) {
		return service.buscarPorId(id);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Integer id, Genero genero) {
		genero.setId(id);
		service.atualizar(genero);
	}

	@GET
    public List<Genero> findAll() {       
        return service.buscarTodos();
    }
}
