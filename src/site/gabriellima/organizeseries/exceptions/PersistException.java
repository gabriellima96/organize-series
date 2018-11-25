package site.gabriellima.organizeseries.exceptions;

public class PersistException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistException(String message) {
		super(message);
	}
	
	public PersistException(String message, Exception ex) {
		super(message, ex);
	}
	
}
