package site.gabriellima.organizeseries.resources;

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

import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.entities.Series;
import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.entities.dto.SeriesDTO;
import site.gabriellima.organizeseries.repositories.SeriesRepositoryImp;
import site.gabriellima.organizeseries.repositories.UserRepositoryImp;
import site.gabriellima.organizeseries.validations.SeriesValidation;

@Path("/{userId}/series")
@Produces(value = MediaType.APPLICATION_JSON)
public class SeriesResource {

	private SeriesRepositoryImp seriesRepository = new SeriesRepositoryImp();

	private UserRepositoryImp userRepository = new UserRepositoryImp();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(@PathParam("userId") Integer userId, Series series) {
		List<ResponseError> errors = SeriesValidation.verify(series);

		if (!errors.isEmpty())
			return Response.status(400).entity(errors).build();

		try {
			User user = userRepository.findById(userId);

			if (user == null) {
				return Response.status(400).entity(
						new ResponseError("Erro ao cadastrar", "Não foi encontrado nenhum usuário com o id " + userId))
						.build();
			}

			series.setUser(user);

			seriesRepository.save(series);
			return Response.status(201).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@GET
	public Response findAll(@PathParam("userId") Integer userId) {
		try {
			User user = userRepository.findById(userId);

			if (user == null) {
				return Response.status(400).entity(
						new ResponseError("Erro ao cadastrar", "Não foi encontrado nenhum usuário com o id " + userId))
						.build();
			}

			List<SeriesDTO> series = seriesRepository.findAll(userId);
			return Response.ok().entity(series).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("userId") Integer userId, @PathParam("id") Integer id) {
		try {
			User user = userRepository.findById(userId);

			if (user == null)
				return Response.status(400).entity(
						new ResponseError("Erro na busca", "Não foi encontrado nenhum usuário com o id " + userId))
						.build();

			SeriesDTO series = seriesRepository.findByIdAndUserId(id, userId);

			if (series == null)
				return Response.status(404)
						.entity(new ResponseError("Erro na busca", "Não foi encontrado nenhuma serie com o id " + id))
						.build();

			return Response.ok().entity(series).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteById(@PathParam("userId") Integer userId, @PathParam("id") Integer id) {
		try {
			User user = userRepository.findById(userId);

			if (user == null)
				return Response.status(400).entity(
						new ResponseError("Erro ao deletar", "Não foi encontrado nenhum usuário com o id " + userId))
						.build();

			if (seriesRepository.remove(id, userId))
				return Response.accepted().build();

			return Response.status(400)
					.entity(new ResponseError("Erro no delete", "A serie não existe ou não é possível deletar"))
					.build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("{id}")
	public Response updateById(@PathParam("userId") Integer userId, @PathParam("id") Integer id, Series series) {
		List<ResponseError> errors = SeriesValidation.verify(series);

		if (!errors.isEmpty())
			return Response.status(400).entity(errors).build();
		
		try {
			User user = userRepository.findById(userId);

			if (user == null)
				return Response.status(400).entity(
						new ResponseError("Erro ao atualizar", "Não foi encontrado nenhum usuário com o id " + userId))
						.build();

			SeriesDTO seriesObj = seriesRepository.findByIdAndUserId(id, userId);
			
			if (seriesObj == null)
				return Response.status(400)
				.entity(new ResponseError("Erro ao atualizar", "A serie não existe ou não é possível atualizar"))
				.build();

			series.setId(seriesObj.getId());
			series.setUser(new User(userId, null, null, null));
			
			seriesRepository.update(series);
			
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Erro interno", e.getMessage())).build();
		}
	}
}