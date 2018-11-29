package site.gabriellima.organizeseries.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import site.gabriellima.organizeseries.entities.Credentials;
import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.repositories.UserRepositoryImp;
import site.gabriellima.organizeseries.validations.UserValidation;

@Path("/users")
public class UserResource {

	private UserRepositoryImp userRepository = new UserRepositoryImp();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response save(User user) {

		List<ResponseError> errors = UserValidation.verify(user);

		if (!errors.isEmpty())
			return Response.status(400).entity(errors).build();

		try {
			User userObj = userRepository.findOneByEmail(user.getEmail());

			if (userObj != null)
				return Response.status(400).entity(new ResponseError("Erro no cadastro", "O email já foi cadastrado"))
						.build();

			userRepository.save(user);
			return Response.status(201).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Error interno", e.getMessage())).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		try {
			if (userRepository.remove(id))
				return Response.accepted().build();

			return Response.status(400).entity(
					new ResponseError("Erro no delete", "O usuário não existe ou não é possível deletar o usuário"))
					.build();
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Error interno", e.getMessage())).build();
		}
	}

	@POST
	@Path("/auth")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response auth(Credentials credentials) {
		try {
			User user = userRepository.findOneByEmail(credentials.getEmail());

			if (user != null && user.getPassword().equals(credentials.getPassword())) {
				return Response.ok().entity(user).build();
			} else {
				return Response.status(401)
						.entity(new ResponseError("Erro ao tentar entrar", "O email ou/e senha são inválidos")).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(new ResponseError("Error interno", e.getMessage())).build();
		}
	}
}
