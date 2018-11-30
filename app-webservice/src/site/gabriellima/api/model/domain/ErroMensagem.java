package site.gabriellima.api.model.domain;

import java.io.Serializable;

public class ErroMensagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mensagem;
    private int codigo;
     
    public ErroMensagem() {
    	
    }

	public ErroMensagem(String mensagem, int codigo) {
		super();
		this.mensagem = mensagem;
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
