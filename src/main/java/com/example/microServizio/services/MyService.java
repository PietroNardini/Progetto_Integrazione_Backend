package com.example.microServizio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microServizio.types.Dipendente;

import jakarta.transaction.Transactional;

import com.example.microServizio.repository.MyRepository;

import java.util.List;
import java.util.Optional;
@Service
public class MyService {
    
    @Autowired
    private MyRepository myRepository;
    
    public List<Dipendente> FindEmployee(String key,String value){
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
    public Dipendente FindEmployeeById(Integer id){
        Optional<Dipendente> results=myRepository.findById(id.longValue());
        return results.orElse(null);
    }
    @Transactional
    public String insert(Dipendente record) throws Exception {
        if (record.isNull()) {
            return "There are one or more null fields";
        }
        if (record.getId() != null && myRepository.existsById(record.getId())) {
            return "The record already exists";
        }
        myRepository.save(record);
        return "Record inserted";
    }
    
}
