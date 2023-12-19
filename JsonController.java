package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/EmissionJson")  // Adjust the mapping to match your resource
public class JsonController {

    private final JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmissionJson> getUserById(@PathVariable Long id)
    {
    	EmissionJson EmissionJson = jsonService.getEmissionJsonById(id);
    	if (EmissionJson != null) {
    		return new ResponseEntity<>(EmissionJson, HttpStatus.OK);
    		} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    }

    @PostMapping("/post/")
    public ResponseEntity<EmissionJson> addEmissionJson(@RequestBody EmissionJson EmissionJson) {
    	EmissionJson savedEmissionJson = jsonService.addEmissionJson(EmissionJson);
    	return new ResponseEntity<>(savedEmissionJson, HttpStatus.CREATED);
    	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    jsonService.deleteEmissionJson(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EmissionJson> updateEmissionJson(@PathVariable Long id, @RequestBody EmissionJson EmissionJson) {
    EmissionJson updatedEmissionJson = jsonService.updateEmissionJson(id, EmissionJson);
    if (updatedEmissionJson != null) {
    return new ResponseEntity<>(updatedEmissionJson, HttpStatus.OK);
    } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
    // You can add more methods as needed
}

