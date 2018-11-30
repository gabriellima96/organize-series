package site.gabriellima.api.model.repository;

import java.util.Set;

import site.gabriellima.api.model.domain.Genero;

public interface GeneroRepository extends CRUDRepository<Genero, Integer>{

	public Set<Genero> findAllBySerie_id(Integer serieId);
}
