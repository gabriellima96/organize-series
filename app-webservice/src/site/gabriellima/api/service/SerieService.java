package site.gabriellima.api.service;

import java.util.List;

import site.gabriellima.api.model.dao.SerieDAO;
import site.gabriellima.api.model.domain.Serie;

public class SerieService {

	private SerieDAO dao = new SerieDAO();
    
    public void salvar(Serie obj) {
    	dao.save(obj);
    }
    
    public void deletar(Integer id) {
        dao.remove(id);
    }
    
    public Serie buscarPorId(Integer id) {
        return dao.findById(id);
    }
    
	public void atualizar(Serie obj) {
		dao.update(obj);
	}
	
	public List<Serie> buscarTodos(Integer usuarioId) {
        return dao.findAll(usuarioId);
    }

}
