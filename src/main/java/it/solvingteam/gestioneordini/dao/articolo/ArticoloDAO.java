package it.solvingteam.gestioneordini.dao.articolo;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo>{

	Articolo findByDescrizione(String descrizione) throws Exception;
	
	public void addCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception;

	public void removeCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception;

	void prezziTotaliPerCategoria(Categoria categoria) throws Exception;

}
