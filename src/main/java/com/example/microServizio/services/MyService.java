package com.example.microServizio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microServizio.types.Dipendente;

import jakarta.transaction.Transactional;

import com.example.microServizio.repository.MyRepository;

import java.util.Optional;
@Service
public class MyService {
    
    @Autowired
    private MyRepository myRepository;
    
    public Dipendente FindEmployee(Integer id){
        Optional<Dipendente> results=myRepository.findById(id.longValue());
        return results.orElse(null);
    }
    @Transactional
    public void insert(Dipendente record) throws Exception {
        if (record.getId() != null) {
            Optional<Dipendente> existingRecord = myRepository.findById(record.getId().longValue());
            if (existingRecord.isPresent()) {
                Dipendente existing = existingRecord.get();
                existing.setNome(record.getNome());
                existing.setCognome(record.getCognome());
                existing.setRuolo(record.getRuolo());
                existing.setUserId(record.getUserId());
                existing.setBancaOre(record.getbancaOre());
                myRepository.save(existing);
            } else {
                myRepository.save(record);
            }
        } else {
            myRepository.save(record);
        }
    }
}
