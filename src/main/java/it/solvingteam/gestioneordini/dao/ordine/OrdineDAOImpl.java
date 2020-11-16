package it.solvingteam.gestioneordini.dao.ordine;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;

public class OrdineDAOImpl implements OrdineDAO{
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	@Override
	public Set<Ordine> set() throws Exception {
		return entityManager.createQuery("from Ordine",Ordine.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ordineInstance = entityManager.merge(ordineInstance);
	}

	@Override
	public void insert(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}			
		entityManager.persist(ordineInstance);
	}

	@Override
	public void delete(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ordineInstance));
	}
	
	//METODO che risponde alla richiesta: 1) Voglio tutti gli ordini effettuati per una determinata categoria;

	@Override
	public void findAllByCategoria(Categoria categoria) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("SELECT DISTINCT a.ordine FROM Articolo a JOIN a.categorie c " 
				+ " where a.ordine is not null and c.descrizione like ?1", Ordine.class);
		query.setParameter(1, "%" + categoria.getDescrizione() + "%");
		Set<Ordine> ordine = query.getResultList().stream().collect(Collectors.toSet());
		System.out.println("\n\nDi seguito tutti gli ordini effettuati per la categoria " + categoria.getDescrizione() + " : \n" 
		 + ordine + "\n\n");
	}
}
