package com.example.microServizio.repository;
import com.example.microServizio.types.Dipendente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/* Classe che definisce il repository (database)  */
public interface MyRepository extends JpaRepository<Dipendente, Long> {//definisco i metodi per interagire con il database (le query)
    Dipendente findById(Integer id);
    boolean existsById(Integer id);//metodo per sapere se esistono records con un certo id
    List<Dipendente> findByNome(String nome);
    List<Dipendente> findByCognome(String cognome);
    List<Dipendente> findByRuolo(String ruolo);
}