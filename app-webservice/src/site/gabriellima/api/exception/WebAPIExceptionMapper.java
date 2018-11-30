package site.gabriellima.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import site.gabriellima.api.model.domain.ErroMensagem;

@Provider
public class WebAPIExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException ex) {
		ErroMensagem erro = new ErroMensagem(ex.getMessage(), ex.getResponse().getStatus());

		return Response.status(erro.getCodigo()).entity(erro).type(MediaType.APPLICATION_JSON).build();
	}

}