package site.gabriellima.api.model.domain;

import java.util.HashSet;
import java.util.Set;

public class Serie {
	
	private Integer id;
	private Integer usuarioId;
	private String nome;
	private String detalhes;
	private Integer anoLancamento;
	
	private Set<Genero> generos = new HashSet<>();

	public Serie() {
		
	}

	public Serie(Integer id, Integer usuarioId, String nome, String detalhes, Integer anoLancamento) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.detalhes = detalhes;
		this.anoLancamento = anoLancamento;
	}
	
	public Serie(Integer usuarioId, String nome, String detalhes, Integer anoLancamento) {
		super();
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.detalhes = detalhes;
		this.anoLancamento = anoLancamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Integer getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Set<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(Set<Genero> generos) {
		this.generos = generos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", nome=" + nome + ", detalhes=" + detalhes + ", anoLancamento=" + anoLancamento
				+ "]";
	}
}
