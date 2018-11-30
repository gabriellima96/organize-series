package site.gabriellima.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import site.gabriellima.api.exception.enums.ErroCodigo;
import site.gabriellima.api.model.domain.ErroMensagem;

@Provider
public class DAOExceptionMapper implements ExceptionMapper<DAOException> {

	@Override
	public Response toResponse(DAOException exception) {
		ErroMensagem erro = new ErroMensagem(exception.getMessage(), exception.getCodigo());
		
		if (exception.getCodigo() == ErroCodigo.BAD_REQUEST.getCodigo()) {
			return Response.status(Status.BAD_REQUEST).entity(erro).type(MediaType.APPLICATION_JSON).build();
		} else if (exception.getCodigo() == ErroCodigo.NOT_FOUND.getCodigo()) {
			return Response.status(Status.NOT_FOUND).entity(erro).type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(erro).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
