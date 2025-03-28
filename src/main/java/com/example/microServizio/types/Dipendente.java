package com.example.microServizio.types;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*Definisco la struttura della tabella dipendenti del database */
@Entity //indica che la classe rappresenta una tabella del database
@Table(name = "dipendenti")//specifica il nome della tabella a cui l'entità è mappata
@Data //genera automaticamente getter,setter ,equals e hashCode
@AllArgsConstructor//È un'annotazione di Lombok che genera un costruttore con tutti i campi della classe come parametri.
@NoArgsConstructor//È un'annotazione di Lombok che genera un costruttore senza argomenti.
public class Dipendente {
    @Id//indica la chiave primaria di un entità
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indica che ID è auto incrementante
    private Integer id;
    
    private String nome;     
    
    private String cognome;  
    @Enumerated(EnumType.STRING) 
    /*L'annotazione @Enumerated viene utilizzata per specificare come un campo di tipo enum deve essere mappato nel database.
In questo caso, il valore EnumType.STRING indica che i valori dell'enum saranno salvati come stringhe nel database. */
    private Ruolo ruolo;
    private Double bancaOre=0.0; 
    
    //funzione per evitare errori con attributi che devono essere Non nulli
    public boolean isNull(){
        return ruolo==null||nome==null||cognome==null;//ritorna true se ci sono uno o più campi nulli
    }
    public String toString(){
        return "Dipendente [id=" + id + " nome=" + nome + ", cognome=" + cognome + ", ruolo=" + ruolo + ", bancaOre=" + bancaOre + "]";
    }
    
}