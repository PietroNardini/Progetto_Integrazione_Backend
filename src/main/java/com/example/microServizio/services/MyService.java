package com.example.microServizio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microServizio.types.Dipendente;

import jakarta.transaction.Transactional;

import com.example.microServizio.repository.MyRepository;

import java.util.List;
import java.util.Optional;
@Service // questa annotazione è utilizzata per rappresentare la logica di business o i servizi applicativi.
public class MyService {
    
    @Autowired
    private MyRepository myRepository; //instanzio la repository con le dichiarazione delle query che si trovano in JpaRepository
    public String aggiungiOre(Integer id, Double ore) throws Exception { //questo metodo aggiunge le ore alla banca ore del dipendente con l'id passato in input
        try{
            Optional<Dipendente> results=myRepository.findById(id.longValue());
            if(results.isPresent()){
                Dipendente dipendente=results.get();
                dipendente.setBancaOre(dipendente.getBancaOre()+ore);
                myRepository.save(dipendente);
                return "ore aggiunte con successo";
            }
            else{
                return "non esiste nessun dipendente con questo id";
            }
        }
        catch (Exception e){
            return "errore: "+e.getMessage();
        }
        
    }
    public List<Dipendente> FindEmployee(String key,String value){ //questo metodo ritorna una lista di dipendenti con l'attributo(key) di un certo valore(value)
        if(key.equals("nome")){
            return myRepository.findByNome(value);
        }
        else if(key.equals("cognome")){
            return myRepository.findByCognome(value);
        }
        else if(key.equals("ruolo")){
            return myRepository.findByRuolo(value);
        }
        else{
            return null;
        }
    }
    public Dipendente FindEmployeeById(Integer id){//trova il dipendente con l'id passato in input e lo ritorna oppura ritorna nullo se non trova nessun impiegato
        Optional<Dipendente> results=myRepository.findById(id.longValue());
        return results.orElse(null);
    }
    @Transactional
    /**
     * Inserisce un nuovo record di tipo Dipendente nel database.
     * 
     * @param record Il record da inserire, di tipo Dipendente.
     * @return Una stringa che indica il risultato dell'operazione:
     *         - "There are one or more null fields" se uno o più campi del record sono null.
     *         - "The record already exists" se il record con lo stesso ID esiste già.
     *         - "Record inserted" se l'inserimento è avvenuto con successo.
     * @throws Exception Se si verifica un errore durante l'operazione.
     */
    public String insert(Dipendente record) throws Exception {
        if (record.isNull()) { // Controlla se il record contiene campi null
            return "There are one or more null fields";
        }
        if (record.getId() != null && myRepository.existsById(record.getId())) { // Verifica se il record esiste già
            return "The record already exists";
        }
        myRepository.save(record); // Salva il record nel database
        return "Record inserted"; // Ritorna un messaggio di successo
    }
    
}
