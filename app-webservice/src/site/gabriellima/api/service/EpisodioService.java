package site.gabriellima.api.service;

import java.util.List;

import site.gabriellima.api.model.dao.EpisodioDAO;
import site.gabriellima.api.model.domain.Episodio;

public class EpisodioService {

	private EpisodioDAO dao = new EpisodioDAO();
    
    public void salvar(Episodio obj) {
    	dao.save(obj);
    }
    
    public void deletar(Integer id) {
        dao.remove(id);
    }
    
    public Episodio buscarPorId(Integer id) {
        return dao.findById(id);
    }
    
	public void atualizar(Episodio obj) {
		dao.update(obj);
	}
	
	public List<Episodio> buscarTodos(Integer serieId) {
        return dao.findAll(serieId);
    }
}
