package it.solvingteam.gestioneordini.model.articolo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.solvingteam.gestioneordini.model.categoria.Categoria;
import it.solvingteam.gestioneordini.model.ordine.Ordine;

@Entity
@Table(name = "articolo")
public class Articolo {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		@Column(name = "descrizione")
		private String descrizione;
		@Column(name = "prezzoSingolo")
		private Double prezzoSingolo;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "ordine_id")
		private Ordine ordine;

		@ManyToMany
		@JoinTable(name = "articolo_categoria", joinColumns = @JoinColumn(name = "articolo_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
		private Set<Categoria> categorie = new HashSet<>();

		public Articolo() {
		}
		
		public Articolo(String descrizione, Double prezzoSingolo) {
			super();
			this.descrizione = descrizione;
			this.prezzoSingolo = prezzoSingolo;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescrizione() {
			return descrizione;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public Double getPrezzoSingolo() {
			return prezzoSingolo;
		}

		public void setPrezzoSingolo(Double prezzoSingolo) {
			this.prezzoSingolo = prezzoSingolo;
		}

		public Ordine getOrdine() {
			return ordine;
		}

		public void setOrdine(Ordine ordine) {
			this.ordine = ordine;
		}

		public Set<Categoria> getCategorie() {
			return categorie;
		}

		public void setCategorie(Set<Categoria> categorie) {
			this.categorie = categorie;
		}

		@Override
		public String toString() {
			return "Articolo [id=" + id + ", descrizione=" + descrizione + ", prezzo=" + prezzoSingolo + ", ordine=" + ordine + ", categorie="
					+ categorie.size() + "]"; //se qui inserisci Categorie toString() stai invalidando il LAZY FETCH
		}	
		
		@Override 
		public boolean equals(Object object) {
			if(object instanceof Articolo) {	//controllo che l'oggetto passato in input sia un'istanza di Utente prima di poter fare il cast
				Articolo articolo = (Articolo) object; //faccio il cast di o a all'oggetto Articolo, assegnandolo a un oggetto Articolo di appoggio 
				return descrizione.equals(articolo.getDescrizione()); //effettuo il confronto sull'attributo descrizione
				//ritorno true se il confronto del valore a cui punta la "descrizione" della classe Articolo 
				//coincide con il valore a cui punta la "descrizone" del nuovo oggetto Articolo di appoggio (che ha assunto il valore dell'oggetto passato in input)
			}
			else {
				return this.equals(object); //altrimenti ritorno false in quanto il valore di "descrizione" di object non coincide con quello dell'istanza
			}
		}
}
