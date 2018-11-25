package site.gabriellima.organizeseries.repositories;

import site.gabriellima.organizeseries.entities.User;
import site.gabriellima.organizeseries.exceptions.PersistException;

public interface UserRepository extends GenericRepository<User, Integer> {

	public User findOneByEmail(String email) throws PersistException;
}
