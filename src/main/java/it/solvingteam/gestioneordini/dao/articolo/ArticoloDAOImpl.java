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
		//ho necessità di verificare che l'articolo non sia già presenta a DB prima di inserirlo
		
		if(this.set().size()>0) { //verifico che ci siano degli articoli già presenti. Se ci sono, effettuo il controllo, altrimenti salto all'else
			boolean articoloIsPresente = false;
			for(Articolo articolo:this.set()) {  //ciclo il set di articoli attualmente presenti a DB, richiamando il metodo .set() già implementato
				 //variabile booleana di appoggio per il controllo
			
				if(articolo.equals(articoloInstance)){ //richiamo il mio equals sovrascritto nel model che confronta la descrizione
					articoloIsPresente = true; //richiamo la mia booleana di appoggio per decretare se quella descrizione è già presente a DB
				}
			}
			if (articoloIsPresente) { //se è già presente rimando indietro la richiesta di inserimento e stampo in console il motivo
				System.out.println("\n\n!!ATTENZIONE!! - L'articolo " +articoloInstance.getDescrizione()+ " è già presente!\n\n");
				return;
				
			} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
				entityManager.persist(articoloInstance);
				System.out.println("\n\nHai aggiunto correttamente l'articolo [ " +articoloInstance.getDescrizione()+ " ]\n\n");
			}
			
		} else { //altrimenti eseguo quello che devo fare e stampo in console il risultato
			entityManager.persist(articoloInstance);
			System.out.println("\n\nHai aggiunto correttamente l'articolo [ " +articoloInstance.getDescrizione()+ " ]");
		 }
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		//Ho necessità di verificare che l'articolo non sia stato associato ad un ordine, prima di poterlo cancellare
		TypedQuery<Articolo> query = entityManager.createQuery("FROM Articolo a where a.ordine = ?1",Articolo.class); //query di verifica
		query.setParameter(1, articoloInstance.getOrdine()); //ottengo una lista di articoli in base all'ordine
		
		if(query.getResultList().isEmpty()) { //se la lista di articoli è vuota, allora eseguo la cancellazione e stampo l'esito in console
		entityManager.remove(entityManager.merge(articoloInstance));
		System.out.println("\n\nL'articolo " + articoloInstance.getDescrizione()+ " è stato correttamente eliminato!");
		} else { //altrimenti rimando indietro la richiesta e stampo l'impossibilità di cancellazione in console
			System.out.println("\n\n!!Attenzione! Questo articolo è presente in almeno un ordine!\n\n");
		}
	}

	//Metodo che mi permette di richiamare un articolo da DB per la sua descrizione (al fine di non dover controllare l'id a DB)
	@Override
	public Articolo findByDescrizione(String descrizione) throws Exception {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a from Articolo a where a.descrizione like ?1", Articolo.class)
				.setParameter(1, "%"+descrizione+"%");
		
		return query.getResultStream().findFirst().orElse(null);
	}
	
	//Metodo che verrà richiamato in ArticoloServiceImpl dentro al metodo aggiungiCategoria
	@Override
	public void addCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception {
		articoloEsistente.getCategorie().add(categoriaEsistente);
	}
	//Metodo che verrà richiamato in ArticoloServiceImpl dentro al metodo rimuoviCategoria
	@Override
	public void removeCategoria(Articolo articoloEsistente, Categoria categoriaEsistente) throws Exception {
		articoloEsistente.getCategorie().remove(categoriaEsistente);
	}
	
	//Metodo implementato per rispondere alla richiesta 3) Voglio la somma totale di tutti i prezzi degli articoli legati ad una categoria;
	@Override
	public void prezziTotaliPerCategoria(Categoria categoria) throws Exception{
		TypedQuery<Double> query = entityManager.createQuery("SELECT SUM(a.prezzoSingolo) FROM Articolo a JOIN a.categorie c " 
				+ " where c.descrizione like ?1", Double.class);
		query.setParameter(1, "%" + categoria.getDescrizione() + "%");
		Set<Double> prezzi = query.getResultList().stream().collect(Collectors.toSet());
		System.out.println("\n\nDi seguito i prezzi totali di tutti gli articoli per la Categoria: " + categoria + " : \n" 
		 + prezzi + "\n\n");
	}
}
