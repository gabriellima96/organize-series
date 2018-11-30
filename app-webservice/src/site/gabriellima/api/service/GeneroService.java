package site.gabriellima.api.service;

import java.util.List;

import site.gabriellima.api.model.dao.GeneroDAO;
import site.gabriellima.api.model.domain.Genero;

public class GeneroService {

    private GeneroDAO dao = new GeneroDAO();
    
    public void salvar(Genero obj) {
    	dao.save(obj);
    }
    
    public void deletar(Integer id) {
        dao.remove(id);
    }
    
    public Genero buscarPorId(Integer id) {
        return dao.findById(id);
    }
    
	public void atualizar(Genero obj) {
		dao.update(obj);
	}
	
	public List<Genero> buscarTodos() {
        return dao.findAll();
    }

}
