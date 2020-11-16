package it.solvingteam.gestioneordini.test;

import java.util.HashSet;
import java.util.Set;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.model.articolo.Articolo;
import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;
import it.solvingteam.gestioneordini.service.MyServiceFactory;
import it.solvingteam.gestioneordini.service.articolo.ArticoloService;
import it.solvingteam.gestioneordini.service.categoria.CategoriaService;
import it.solvingteam.gestioneordini.service.ordine.OrdineService;

/**
 * Hello world!
 *
 */

//Voglio tutti gli ordini effettuati per una determinata categoria; TUTTI GLI ORDINI → (PASSA PER ARTICOLI) →  PER UNA DETERMINATA CATEGORIA
//Voglio tutte le categorie degli articoli di un determinato ordine; TUTTE LE CATEGORIE DEGLI ARTICOLI → PER UN DETERMINATO ORDINE
//Voglio la somma totale di tutti i prezzi degli articoli legati ad una categoria; SOMMA PREZZI ARTICOLI PER UNA DETERMINATA CATEGORIA

public class GestioneOrdiniTest {
	
    public static void main( String[] args ) {
    
	ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
	CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
	OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
	// ora passo alle operazioni CRUD
		try {
				
			
			//istanzio alcune categorie
			Categoria categoria = new Categoria("Elettronica");
			Categoria categoria2 = new Categoria("Abbigliamento");
			Categoria categoria3 = new Categoria("Sport");
			Categoria categoria4 = new Categoria("Giardinaggio");
			Categoria categoria5 = new Categoria("Arredamento");
			Categoria categoria6 = new Categoria("Cartoleria");
			Categoria categoria7 = new Categoria("Giochi");
			
			//aggiorno i dati a DB con le categorie appena istanziate 
//			categoriaServiceInstance.inserisciNuovo(categoria);
//			categoriaServiceInstance.inserisciNuovo(categoria2);
//			categoriaServiceInstance.inserisciNuovo(categoria3);
//			categoriaServiceInstance.inserisciNuovo(categoria4);
//			categoriaServiceInstance.inserisciNuovo(categoria5);
//			categoriaServiceInstance.inserisciNuovo(categoria6);
//			categoriaServiceInstance.inserisciNuovo(categoria7);
			
			//inizializzo alcuni articoli
			Articolo articolo = new Articolo("Penna Bic - Nero", 5.10);
			Articolo articolo2 = new Articolo("Guanti invernali - M", 8.30);
			Articolo articolo3 = new Articolo("MacBook Pro", 1400.79);
			Articolo articolo4 = new Articolo("Mountain Bike", 450.00);
			Articolo articolo5 = new Articolo("Elegante lampada a sospensione LED Dominykas", 149.90);
			Articolo articolo6 = new Articolo("Nintendo Switch", 329.00);
			Articolo articolo7 = new Articolo("Forbici", 30.00);
			Articolo articolo8 = new Articolo("Felpa", 20.00);
			Articolo articolo9 = new Articolo("Telecomando 2000", 30.00);
					
			
//			//aggiorno i dati a DB con gli articoli appena istanziati
//			articoloServiceInstance.inserisciNuovo(articolo);
//			articoloServiceInstance.inserisciNuovo(articolo2);
//			articoloServiceInstance.inserisciNuovo(articolo3);
//			articoloServiceInstance.inserisciNuovo(articolo4);
//			articoloServiceInstance.inserisciNuovo(articolo5);
//			articoloServiceInstance.inserisciNuovo(articolo6);
//			articoloServiceInstance.inserisciNuovo(articolo7);
//			articoloServiceInstance.inserisciNuovo(articolo8);
//			articoloServiceInstance.inserisciNuovo(articolo9);
			
			//assegno le categorie aggiunte a DB ai miei articoli aggiunti a DB tramite il metodo "cercaPerDescrizione" implementato in entrambi i DAO
			Categoria categoriaDaDb = categoriaServiceInstance.cercaPerDescrizione("Cartoleria");
			Articolo articoloDaDb = articoloServiceInstance.cercaPerDescrizione("Penna Bic");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb, categoriaDaDb);
//			}
//			
			Categoria categoriaDaDb2 = categoriaServiceInstance.cercaPerDescrizione("Abbig");
			Articolo articoloDaDb2 = articoloServiceInstance.cercaPerDescrizione("Guanti invernali");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb2, categoriaDaDb2);
//			}
//			
			Categoria categoriaDaDb3 = categoriaServiceInstance.cercaPerDescrizione("Sport");
			Articolo articoloDaDb3 = articoloServiceInstance.cercaPerDescrizione("Mount");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb3, categoriaDaDb3);
//			}
//			
			Categoria categoriaDaDb4 = categoriaServiceInstance.cercaPerDescrizione("Elettro");
			Articolo articoloDaDb4 = articoloServiceInstance.cercaPerDescrizione("MacBook");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb4, categoriaDaDb4);
//			}
//			
			Categoria categoriaDaDb5 = categoriaServiceInstance.cercaPerDescrizione("Arredam");
			Articolo articoloDaDb5 = articoloServiceInstance.cercaPerDescrizione("lampada a sospensione");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb5, categoriaDaDb5);
//			}
//			
			Categoria categoriaDaDb6 = categoriaServiceInstance.cercaPerDescrizione("Elettronica");
			Articolo articoloDaDb6 = articoloServiceInstance.cercaPerDescrizione("Nintendo");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb6, categoriaDaDb6);
//			}
//			
			Categoria categoriaDaDb7 = categoriaServiceInstance.cercaPerDescrizione("Cartoleria");
			Articolo articoloDaDb7 = articoloServiceInstance.cercaPerDescrizione("Forbici");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb7, categoriaDaDb7);
//			}
//			
//			//inserisco forbici in più categorie
			Categoria categoriaDaDb8 = categoriaServiceInstance.cercaPerDescrizione("Giardinaggio");
			Articolo articoloDaDb8 = articoloServiceInstance.cercaPerDescrizione("Forbici");
//			if (articoloDaDb != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb8, categoriaDaDb8);
//			}
//			
//			//inserisco forbici in più categorie per verificare la correttezza della relazione "Many to Many"
//			Categoria categoriaDaDb9 = categoriaServiceInstance.cercaPerDescrizione("Sport");
			Articolo articoloDaDb9 = articoloServiceInstance.cercaPerDescrizione("Felpa");
//			if (articoloDaDb9 != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb9, categoriaDaDb9);
//			}
			
//			//assegno la categoria "Elletronica" a un altro articolo
			Articolo articoloDaDb10 = articoloServiceInstance.cercaPerDescrizione("Telecoman");
//			if(articoloDaDb10 != null) {
//				articoloServiceInstance.aggiungiCategoria(articoloDaDb10, categoriaDaDb4);
//			}
			
			
			//istanzio alcuni ordini
			
			Ordine ordine = new Ordine("Giacomo Sincope", "Via dei malanni, 1");
			Ordine ordine2 = new Ordine("Ludovica Celsi", "Via del pianeta terra, 66");
			Ordine ordine3 = new Ordine("Benedetta Giaquinto", "Via del mal di testa, 80");
			Ordine ordine4 = new Ordine("Osvaldo Settiminio", "Via degli improbabili, 100");
			Ordine ordine5 = new Ordine("Tito Caio", "Via ignota, 22");
			
						
			//L'INSERT DI ORDINE CREA ORDINE -> PRIMA DI CREARE L'ORDINE DEVO SETTARE TUTTI GLI ARTICOLI D'INTERESSE PRENDENDO LA LISTA DI ARTICOLI DA DB!!!
			
			Set<Articolo> articoliPerOrdine1 = new HashSet<>(3);
			articoliPerOrdine1.add(articoloDaDb);
			articoliPerOrdine1.add(articoloDaDb9);
			articoliPerOrdine1.add(articoloDaDb10);
			ordine5.setArticoli(articoliPerOrdine1); //importo all'ordine la lista di articoli estrapolati da DB)
			
			ordineServiceInstance.inserisciNuovo(ordine5); //inserisco 
			for(Articolo articoloDaMergiare:articoliPerOrdine1) {
			ordineServiceInstance.inserisciArticolo(ordine5, articoloDaMergiare);
			}
			
			//aggiorno i dati a DB con gli ordini appena istanziati
//			ordineServiceInstance.inserisciNuovo(ordine);
//			ordineServiceInstance.inserisciNuovo(ordine2);
//			ordineServiceInstance.inserisciNuovo(ordine3);
//			ordineServiceInstance.inserisciNuovo(ordine4);
			
			Ordine ordineArt = ordineServiceInstance.trova(1L);
			Ordine ordineArt2 = ordineServiceInstance.trova(2L);
			Ordine ordineArt3 = ordineServiceInstance.trova(3L);
			Ordine ordineArt4 = ordineServiceInstance.trova(4L);
			
			//non potendo esistere ordini senza articoli, assegno contestualmente alcuni articoli agli ordini inseriti
//			ordineServiceInstance.inserisciArticolo(ordineArt, articoloDaDb9);
//			ordineServiceInstance.inserisciArticolo(ordineArt2, articoloDaDb6);
//			ordineServiceInstance.inserisciArticolo(ordineArt2, articoloDaDb4);
//			ordineServiceInstance.inserisciArticolo(ordineArt4, articoloDaDb7);
//			ordineServiceInstance.inserisciArticolo(ordineArt3, articoloDaDb5);
//			ordineServiceInstance.inserisciArticolo(ordineArt3, articoloDaDb10);
			
			//testo il metodo che rimuove articolo con il vincolo sull'ordine
//			articoloServiceInstance.rimuovi(articoloDaDb4);
			
			//testo il metodo che rimuove la categoria con il vincolo sull'articolo
//			Categoria CatDaDb = categoriaServiceInstance.cercaPerDescrizione("giochi");
//			categoriaServiceInstance.rimuovi(CatDaDb);
			
			
			//Prova update
//			Categoria categoriaDaDb9 = categoriaServiceInstance.cercaPerDescrizione("Sport");
//			categoriaDaDb9.setDescrizione("Sports");
//			categoriaServiceInstance.aggiorna(categoriaDaDb9);
			
			
			
			//------------------------------------------DOMANDE------------------------------------------//
			//QUERY 1
			//implementato metodo findAllByCategoria in OrdineDAOImpl
//			System.out.println("\n\n##### Tutti gli ordini effettuati per una determinata categoria #####\n\n");
//			ordineServiceInstance.findAllByCategoria(categoriaDaDb6); //questa è quella giusta
			
			//QUERY 2
			//implementato metodo findAllByOrdine in CategoriaDAOImpl
//			System.out.println("\n\n##### Tutte le categorie degli articoli di un determinato ordine #####\n\n");
//			categoriaServiceInstance.trovaByOrdine(ordineArt3);
			
			//QUERY 3
			//prezziTotaliPerCategoria in ArticoloDAOImpl
//			System.out.println("\n\n##### Somma totale di tutti i prezzi degli articoli legati ad una categoria; #####\n\n");
//			articoloServiceInstance.prezziTotaliPerCategoria(categoriaDaDb4);
			
			
			
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}
  
}

