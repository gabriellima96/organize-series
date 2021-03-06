package site.gabriellima.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloWorld {
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		return Response.ok().entity("Hello World").build();
	}

}
