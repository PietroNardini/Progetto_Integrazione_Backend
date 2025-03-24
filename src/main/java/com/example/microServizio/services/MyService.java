package com.example.microServizio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microServizio.types.Dipendente;
import com.example.microServizio.repository.MyRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class MyService {
    
    @Autowired
    private MyRepository myRepository;
    
    public Dipendente FindEmployee(Integer id){
        Optional<Dipendente> results=myRepository.findById(id.longValue());
        return results.orElse(null);
    }
    
}
