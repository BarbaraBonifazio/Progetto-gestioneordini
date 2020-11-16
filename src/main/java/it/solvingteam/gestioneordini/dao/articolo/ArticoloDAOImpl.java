package it.solvingteam.gestioneordini.dao.articolo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public Set<Articolo> set() throws Exception {
		return entityManager.createQuery("from Articolo",Articolo.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		articoloInstance = entityManager.merge(articoloInstance);
	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		
// ------ NON E' NECESSARIO FARE UN CONTROLLO SULL'INSERIMENTO DI UN NUOVO ARTICOLO IN QUANTO L'ARTICOLO RAPPRESENTA IL SINGOLO OGGETTO FISICO E NON IL DB ------

				entityManager.persist(articoloInstance);
				System.out.println("\n\nHai aggiunto correttamente l'articolo [ " +articoloInstance.getDescrizione()+ " ]\n\n");
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoloInstance));
	}

	//Metodo che mi permette di richiamare un articolo da DB per la sua descrizione (al fine di non dover controllare l'id a DB)
	@Override
	public Articolo findByDescrizione(String descrizione) throws Exception {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a from Articolo a where a.descrizione like ?1", Articolo.class)
				.setParameter(1, "%"+descrizione+"%");
		
		return query.getResultStream().findFirst().orElse(null);
	}
	
	//Metodo implementato per rispondere alla richiesta 3) Voglio la somma totale di tutti i prezzi degli articoli legati ad una categoria;
	@Override
	public void prezziTotaliPerCategoria(Categoria categoria) throws Exception{
		TypedQuery<Double> query = entityManager.createQuery("SELECT SUM(a.prezzoSingolo) FROM Articolo a JOIN a.categorie c " 
				+ " where c.descrizione like ?1", Double.class);
		query.setParameter(1, "%" + categoria.getDescrizione() + "%");
		Double prezzo = query.getSingleResult();
		System.out.println("\n\nDi seguito il prezzo totale di tutti gli articoli per la Categoria: " + categoria + " : \n" 
		 + prezzo + "\n\n");
	}	
}
