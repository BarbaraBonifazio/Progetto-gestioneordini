package it.solvingteam.gestioneordini.model.categoria;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import it.solvingteam.gestioneordini.model.articolo.Articolo;

@Entity
@Table(name = "categoria")
public class Categoria {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		@Column(name = "descrizione")
		private String descrizione;
		
		@ManyToMany(mappedBy = "categorie")
		private Set<Articolo> articoli = new HashSet<>();
		
		public Categoria() {
		}
		
		public Categoria(String descrizione) {
			super();
			this.descrizione = descrizione;
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

		public Set<Articolo> getArticoli() {
			return articoli;
		}

		public void setArticoli(Set<Articolo> articoli) {
			this.articoli = articoli;
		}

		@Override
		public String toString() {
			return "[id: " + id + ", descrizione: " + descrizione + "]";
		}
		
		@Override 
		public boolean equals(Object object) {
			if(object instanceof Categoria) {	//controllo che l'oggetto passato in input sia un'istanza di Utente prima di poter fare il cast
				Categoria categoria = (Categoria) object; //faccio il cast di o a all'oggetto Categoria, assegnandolo a un oggetto Categoria di appoggio 
				return descrizione.equals(categoria.getDescrizione()); //effettuo il confronto sull'attributo descrizione
				//ritorno true se il confronto del valore a cui punta la "descrizione" della classe Categoria 
				//coincide con il valore a cui punta la "descrizone" del nuovo oggetto Categoria di appoggio (che ha assunto il valore dell'oggetto passato in input)
			}
			else {
				return this.equals(object); //altrimenti ritorno false in quanto il valore di "descrizione" di object non coincide con quello dell'istanza
			}
		}
	}

