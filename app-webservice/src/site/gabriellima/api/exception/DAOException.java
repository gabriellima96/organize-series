package site.gabriellima.api.exception;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 3965087475900464946L;

	private int codigo;

	public DAOException(String mensagem, int codigo) {
		super(mensagem);
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

}
