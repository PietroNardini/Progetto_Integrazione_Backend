package com.example.microServizio.api;

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
@RequestMapping("/api")
public class MainController {
    @Autowired
    private MyService myService;
    @PostMapping("/readId")
    public ResponseEntity<String> readId(@RequestBody Map<String, Integer> requestBody) {
        try{
            Integer id = requestBody.get("id");
            Dipendente record = myService.FindEmployee(id);
            if (record == null) {
                return ResponseEntity.ok("Employee not found");
            }
            return ResponseEntity.ok(record.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
        
    }
}
