package it.solvingteam.gestioneordini.dao.categoria;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public Set<Categoria> set() throws Exception {
		return entityManager.createQuery("from Categoria",Categoria.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categoriaInstance = entityManager.merge(categoriaInstance);
	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		//ho necessità di verificare che la categoria non sia già presenta a DB prima di inserirla
		
			if(this.set().size()>0) { //verifico che ci siano delle categorie già presenti. Se ci sono, effettuo il controllo, altrimenti salto all'else
				boolean categoriaIsPresente = false;
				for(Categoria categoria:this.set()) {  //ciclo il set di categorie attualmente presenti a DB, richiamando il metodo .list() già implementato
					 //variabile booleana di appoggio per il controllo
				
					if(categoria.equals(categoriaInstance)){ //richiamo il mio equals sovrascritto nel model che confronta la descrizione
						categoriaIsPresente = true; //richiamo la mia booleana di appoggio per decretare che quella descrizione è già presente a DB
					}
				}
				if (categoriaIsPresente) { //se è già presente rimando indietro la richiesta di inserimento e stampo in console il motivo
					System.out.println("\n\n!!ATTENZIONE!! - La categoria " +categoriaInstance.getDescrizione()+ " è già presente!\n\n");
					return;
					
				} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
					entityManager.persist(categoriaInstance);
					System.out.println("\n\nHai aggiunto correttamente la Categoria [" +categoriaInstance.getDescrizione()+ " ]");
				}
				
			} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
				entityManager.persist(categoriaInstance);
				System.out.println("\n\nHai aggiunto correttamente la Categoria [" +categoriaInstance.getDescrizione()+ " ]");
			 }
	}
	

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		
		Set<Articolo> articoli = new HashSet<>();
		//ho necessità di verificare che non ci siano degli articoli associati alla categoria che voglio eliminare 
		//effettuo una query su Articolo per ottenere tutti gli articoli eventualmente associati a quella categoria 
		TypedQuery<Articolo> query = entityManager.createQuery("SELECT a FROM Articolo a join a.categorie cat where cat = :categoria",Articolo.class);
		query.setParameter("categoria", categoriaInstance);
		articoli = query.getResultList().stream().collect(Collectors.toSet());
		
		if(articoli.size()==0) { //se la lista degli articoli ricavati da DB è vuota allora entro nell'if
			System.out.println("\n\nNon ci sono Articoli assegnati a questa Categoria! Puoi procedere con l'eliminazione.\n\n");
			entityManager.remove(entityManager.merge(categoriaInstance)); //eseguo l'eliminazione dopo il merge di eventuali modifiche fatte sulla "nuvoletta"
			System.out.println("\n\nLa categoria è stata correttamente eliminata!\n\n");	
		} else {	//altrimenti rimando indietro la richiesta e stampo in console il motivo
			System.out.println("\n\n !!ATTENZIONE!! La categoria che cerchi di eliminare è stata assegnata ad almeno un articolo pertanto non puoi eliminarla!\n\n");
		}			
	}
	
	//Metodo che mi permette di richiamare una categoria da DB per la sua descrizione (al fine di non dover controllare l'id a DB)
	@Override
	public Categoria findByDescrizione(String descrizione) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c from Categoria c where c.descrizione like ?1", Categoria.class)
				.setParameter(1, "%"+descrizione+"%");
		
		return query.getResultStream().findFirst().orElse(null);
	}
	
	//Metodo implementato per rispondere alla richiesta 2) Voglio tutte le categorie degli articoli di un determinato ordine;
	@Override
	public void findAllByOrdine(Ordine ordine) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery("SELECT DISTINCT c FROM Categoria c JOIN c.articoli a  where a.ordine.id = ?1", Categoria.class);
		query.setParameter(1, ordine.getId());
		Set<Categoria> categoria = query.getResultList().stream().collect(Collectors.toSet());
		System.out.println("\n\nDi seguito le categorie degli articoli in base all'ordine " + ordine.getId() + " : \n" 
		 + categoria + "\n\n");
	}
}
