package site.gabriellima.organizeseries.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import site.gabriellima.organizeseries.entities.Genre;
import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.repositories.GenreRepositoryImp;
import site.gabriellima.organizeseries.validations.GenreValidation;

@Path("/genres")
@Produces(value = MediaType.APPLICATION_JSON)
public class GenreResource {

	private GenreRepositoryImp genreRepository = new GenreRepositoryImp();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Genre genre) {
		List<ResponseError> errors = GenreValidation.verify(genre);

		if (!errors.isEmpty())
			return Response.status(400).entity(errors).build();

		try {
			Genre genreObj = genreRepository.findOneByName(genre.getName());

			if (genreObj != null)
				return Response.status(400).entity(new ResponseError("Erro no cadastro", "O gênero já foi cadastrado"))
						.build();

			genreRepository.save(genre);
			return Response.status(201).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@GET
	public Response findAll() {
		try {
			List<Genre> genres = genreRepository.findAll();
			return Response.ok().entity(genres).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") Integer id) {
		try {
			Genre genre = genreRepository.findById(id);

			if (genre == null)
				return Response.status(404)
						.entity(new ResponseError("Erro na busca", "Não foi encontrado nenhum gênero com o id " + id))
						.build();

			return Response.ok().entity(genre).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteById(@PathParam("id") Integer id) {
		try {
			if (genreRepository.remove(id))
				return Response.accepted().build();

			return Response.status(400)
					.entity(new ResponseError("Erro no delete", "O gênero não existe ou não é possível deletar"))
					.build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}
}
