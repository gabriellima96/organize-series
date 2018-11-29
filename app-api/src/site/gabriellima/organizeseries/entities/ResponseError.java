package site.gabriellima.organizeseries.entities;

public class ResponseError {

	private String titleError;
	private String messageError;

	public ResponseError() {

	}

	public ResponseError(String titleError, String messageError) {
		super();
		this.titleError = titleError;
		this.messageError = messageError;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public String getTitleError() {
		return titleError;
	}

	public void setTitleError(String titleError) {
		this.titleError = titleError;
	}
}
