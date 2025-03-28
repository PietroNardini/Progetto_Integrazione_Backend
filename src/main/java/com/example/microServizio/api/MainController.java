package com.example.microServizio.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microServizio.services.MyService;
import com.example.microServizio.types.Dipendente;

@RestController

/*Indica che questa classe è un controller REST, progettato per gestire richieste HTTP e restituire risposte JSON o altri formati direttamente nel corpo della risposta. */
@RequestMapping("/api")//tutte le richieste a questa classe inizieranno con /api.
public class MainController {
    @Autowired//permette di iniettare automaticamente l'istanza di un Bean in un altra classe
    private MyService myService;//instanzio la classe in cui ci sono le implementazioni dei metodi per interfacciarsi con il database
    @PostMapping("/FilterBy")//indica che questo metodo ascolta all'endpoint FilterBy
    public ResponseEntity <String> SearchBy(@RequestBody Map<String, String> requestBody) {//metodo per cercare in base a un attributo diverso dalla chiave primaria
        try{
            Map.Entry<String, String> res = requestBody.entrySet().iterator().next();//ottengo un entry della mappa data in input al metodo
            String key = res.getKey();//assegno a delle variabili la chiave e il valore della entry 
            String value = res.getValue();
            
            if (key == null || value == null) {//controllo che non manchi la chiave oppure il valore
                return ResponseEntity.ok("Error: missing key or value");
            }
            List<Dipendente> results=myService.FindEmployee(key, value);
            if(results==null){
                return ResponseEntity.ok("attributo sbagliato");
            }
            if(!results.isEmpty()){
                return ResponseEntity.ok(results.toString());
            }
            else{
                return ResponseEntity.ok("there was no employee with "+key+" = "+value);
            }
        }
        catch (Exception e){
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
    @PostMapping("/readId")//questo metodo ascolta all'endpoint /readId
    public ResponseEntity<String> readId(@RequestBody Map<String, Integer> requestBody) {//metodo per cercare un impiegato in base all'Id
        try{
            Integer id = requestBody.get("id");//ottengo l'id per cui cercare
            Dipendente record = myService.FindEmployeeById(id);//effettuo la ricerca
            if (record == null) {// se non viene trovato nessun Dipendente con quell'ID
                return ResponseEntity.ok("Employee not found");
            }
            return ResponseEntity.ok(record.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
        
    }
    @PostMapping("/saveRecord") //questo metodo ascolta all'endpoint /saveRecord
    public ResponseEntity<Map<String, String>> saveRecord(@RequestBody Dipendente record) {//metodo per aggiungere alla table un dipendente dato in input
        try{
            String results=myService.insert(record);//inserisco il valore nel database e ricevo risposta su come è andata l'inserzione
            Map<String, String> response = new HashMap<>();
            response.put("message", results);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
        
    }
}
