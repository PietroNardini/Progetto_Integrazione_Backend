package com.example.microServizio.types;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dipendenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer userId;  
    
    private String nome;     
    
    private String cognome;  
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
    private Double bancaOre; 
    public Integer getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public Integer getUserId(){
        return userId;
    }
    public Double getbancaOre(){
            return bancaOre;
    }
    public Ruolo getRuolo(){
        return ruolo;
    }
    public String toString(){
        return "Dipendente [id=" + id + ", userId=" + userId + ", nome=" + nome + ", cognome=" + cognome + ", ruolo=" + ruolo + ", bancaOre=" + bancaOre + "]";
    }
    
}