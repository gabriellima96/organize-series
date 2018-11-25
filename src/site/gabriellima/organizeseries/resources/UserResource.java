package site.gabriellima.organizeseries.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.exceptions.PersistException;
import site.gabriellima.organizeseries.repositories.UserRepositoryImp;

@Path("/users")
public class UserResource {

	private UserRepositoryImp userRepository = new UserRepositoryImp();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response save(User user) throws PersistException {
		userRepository.save(user);
		return Response.ok().entity(user).build();
	}
}
