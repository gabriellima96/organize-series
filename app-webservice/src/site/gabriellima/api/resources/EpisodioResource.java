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

import site.gabriellima.api.model.domain.Episodio;
import site.gabriellima.api.service.EpisodioService;

@Path("/{episodioId}/{episodioId}/episodios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EpisodioResource {

	private EpisodioService service = new EpisodioService();

	@POST
	public Response save(@PathParam("episodioId") Integer episodioId, Episodio episodio) {
		episodio.setSerieId(episodioId);
		service.salvar(episodio);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		service.deletar(id);
	}

	@GET
	@Path("{id}")
	public Episodio findById(@PathParam("id") Integer id) {
		return service.buscarPorId(id);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("episodioId") Integer episodioId, @PathParam("id") Integer id, Episodio episodio) {
		episodio.setSerieId(episodioId);
		episodio.setId(id);
		service.atualizar(episodio);
	}

	@GET
    public List<Episodio> findAll(@PathParam("episodioId") Integer episodioId) {       
        return service.buscarTodos(episodioId);
    }
	
}
