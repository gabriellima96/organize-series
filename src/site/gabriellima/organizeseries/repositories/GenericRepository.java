package site.gabriellima.organizeseries.repositories;

import java.util.List;

import site.gabriellima.organizeseries.exceptions.PersistException;

public interface GenericRepository<Type, Id> {

	public Boolean save(Type obj) throws PersistException;

	public Type update(Type obj) throws PersistException;

	public Type remove(Id id) throws PersistException;

	public List<Type> findAll() throws PersistException;

	public Type findById(Id id) throws PersistException;
}
