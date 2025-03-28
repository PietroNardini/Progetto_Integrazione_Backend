package com.example.microServizio.api;

import java.util.HashMap;
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
    @PostMapping("/FilterBy")
    public ResponseEntity <String> FilterBy(@RequestBody Map<String, String> requestBody) {
        try{
            Map.Entry<String, String> res = requestBody.entrySet().iterator().next();
            String key = res.getKey();
            String value = res.getValue();
            
            if (key == null || value == null) {
                return ResponseEntity.ok("Error: missing key or value");
            }
            return ResponseEntity.ok(myService.FindEmployee(key, value).toString());
        }
        catch (Exception e){
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
    @PostMapping("/readId")
    public ResponseEntity<String> readId(@RequestBody Map<String, Integer> requestBody) {
        try{
            Integer id = requestBody.get("id");
            Dipendente record = myService.FindEmployeeById(id);
            if (record == null) {
                return ResponseEntity.ok("Employee not found");
            }
            return ResponseEntity.ok(record.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
        
    }
    @PostMapping("/saveRecord") // New endpoint to save data
    public ResponseEntity<Map<String, String>> saveRecord(@RequestBody Dipendente record) {
        try{
            String results=myService.insert(record);
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
