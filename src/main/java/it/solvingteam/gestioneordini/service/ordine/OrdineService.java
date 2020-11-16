package it.solvingteam.gestioneordini.service.ordine;

import java.util.Set;

import it.solvingteam.gestioneordini.dao.ordine.OrdineDAO;
import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;

public interface OrdineService {

	public Set<Ordine> setAll() throws Exception;

	public Ordine trova(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;

	void inserisciArticolo(Ordine ordineEsistente, Articolo articoloEsistente) throws Exception;
	
	void rimuoviArticolo(Ordine ordineEsistente, Articolo articoloEsistente) throws Exception;
	
	void findAllByCategoria(Categoria categoria) throws Exception;
	
	// per injection
	public void setOrdineDAO(OrdineDAO ordineDAO);

}
