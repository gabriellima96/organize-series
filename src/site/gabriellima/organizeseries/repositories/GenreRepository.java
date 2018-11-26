package site.gabriellima.organizeseries.repositories;

import java.util.Set;

import site.gabriellima.organizeseries.entities.Genre;
import site.gabriellima.organizeseries.exceptions.PersistException;

public interface GenreRepository extends GenericRepository<Genre, Integer> {

	public Genre findOneByName(String name) throws PersistException;
	public Set<Genre> findAllBySerie_id(Integer id) throws PersistException;
}
