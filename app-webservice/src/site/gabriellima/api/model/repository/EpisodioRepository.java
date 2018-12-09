package site.gabriellima.api.model.repository;

import java.util.List;

import site.gabriellima.api.model.domain.Episodio;

public interface EpisodioRepository extends CRUDRepository<Episodio, Integer> {

	public List<Episodio> findAll(Integer serieId);
}
