package site.gabriellima.organizeseries.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import site.gabriellima.organizeseries.entities.ResponseError;
import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.exceptions.PersistException;
import site.gabriellima.organizeseries.repositories.UserRepositoryImp;
import site.gabriellima.organizeseries.validations.UserValidation;

@Path("/users")
public class UserResource {

	private UserRepositoryImp userRepository = new UserRepositoryImp();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response save(User user) throws PersistException {

		List<ResponseError> errors = UserValidation.verify(user);

		if (!errors.isEmpty())
			return Response.status(400).entity(errors).build();

		User userObj = userRepository.findOneByEmail(user.getEmail());

		if (userObj != null)
			return Response.status(400).entity(new ResponseError("Erro no cadastro", "O email já foi cadastrado")).build();

		userRepository.save(user);
		return Response.status(201).build();
	}
}
