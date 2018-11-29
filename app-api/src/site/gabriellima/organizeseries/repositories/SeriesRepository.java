package site.gabriellima.organizeseries.repositories;

import java.util.List;

import site.gabriellima.organizeseries.entities.Series;
import site.gabriellima.organizeseries.entities.dto.SeriesDTO;
import site.gabriellima.organizeseries.exceptions.PersistException;

public interface SeriesRepository extends GenericRepository<Series, Integer> {

	public List<SeriesDTO> findAll(Integer userId) throws PersistException;

	public SeriesDTO findByIdAndUserId(Integer id, Integer userId) throws PersistException;

	public Boolean remove(Integer id, Integer userId) throws PersistException;
}
