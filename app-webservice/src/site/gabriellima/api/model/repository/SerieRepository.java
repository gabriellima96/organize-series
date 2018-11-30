package site.gabriellima.api.model.repository;

import java.util.List;

import site.gabriellima.api.model.domain.Serie;

public interface SerieRepository extends CRUDRepository<Serie, Integer> {
	
	public List<Serie> findAll(Integer userId);
}
