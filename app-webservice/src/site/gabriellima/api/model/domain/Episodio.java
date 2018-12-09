package site.gabriellima.api.model.domain;

public class Episodio {
	
	private Integer id;
	private Integer serieId;
	
	private String titulo;
	private String detalhes;
	private Boolean assisti;
	private Integer temporada;
	
	public Episodio() {
		
	}

	public Episodio(Integer id, Integer serieId, String titulo, String detalhes, Boolean assisti, Integer temporada) {
		super();
		this.id = id;
		this.serieId = serieId;
		this.titulo = titulo;
		this.detalhes = detalhes;
		this.assisti = assisti;
		this.temporada = temporada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSerieId() {
		return serieId;
	}

	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Boolean getAssisti() {
		return assisti;
	}

	public void setAssisti(Boolean assisti) {
		this.assisti = assisti;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
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
		Episodio other = (Episodio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Episodio [id=" + id + ", serieId=" + serieId + ", titulo=" + titulo + ", detalhes=" + detalhes
				+ ", assisti=" + assisti + ", temporada=" + temporada + "]";
	}
}
