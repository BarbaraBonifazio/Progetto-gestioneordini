package it.solvingteam.gestioneordini.service.articolo;

import java.util.Set;

import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;

public interface ArticoloService {
	
	public Set<Articolo> setAll() throws Exception;

	public Articolo trova(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	public void aggiungiCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception;
	
	void rimuoviCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception;
	
	Articolo cercaPerDescrizione(String descrizione) throws Exception;
	
	void prezziTotaliPerCategoria(Categoria categoria) throws Exception;
	
	// per injection
		public void setArticoloDAO(ArticoloDAO articoloDAO);
	
}
