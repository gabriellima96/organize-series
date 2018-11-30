package site.gabriellima.api.model.repository;

import java.util.List;

public interface CRUDRepository<Tipo, Id> {

	public void save(Tipo obj);

	public void update(Tipo obj);

	public void remove(Id id);

	public List<Tipo> findAll();

	public Tipo findById(Id id);
}
