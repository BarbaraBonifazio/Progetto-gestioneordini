package it.solvingteam.gestioneordini.dao.ordine;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{

	public void findAllByCategoria(Categoria categoria) throws Exception;

}	
