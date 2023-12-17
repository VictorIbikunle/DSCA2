package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emissions")  // Adjust the mapping to match your resource
public class EmissionController {

    private final EmissionService emissionService;

    @Autowired
    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Emission> getEmissionById(@PathVariable Long id)
    {
    	Emission emission = emissionService.getEmissionById(id);
    	if (emission != null) {
    		return new ResponseEntity<>(emission, HttpStatus.OK);
    		} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    }

    @PostMapping("/post/")
    public ResponseEntity<Emission> addEmission(@RequestBody Emission emission) {
    	Emission savedEmission = emissionService.addEmission(emission);
    	return new ResponseEntity<>(savedEmission, HttpStatus.CREATED);
    	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmission(@PathVariable Long id) {
    emissionService.deleteEmission(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/put")
    public ResponseEntity<Emission> updateEmission(@PathVariable Long id, @RequestBody Emission Emission) {
    Emission updatedEmission = emissionService.updateEmission(id, Emission);
    if (updatedEmission != null) {
    return new ResponseEntity<>(updatedEmission, HttpStatus.OK);
    } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    // You can add more methods as needed
}

