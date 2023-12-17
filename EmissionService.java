package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.EmissionRepository;
  // Assuming Emission is your entity

@Service
public class EmissionService {

    private final EmissionRepository emissionRepository;

    @Autowired
    public EmissionService(EmissionRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    public Emission getEmissionById(Long emissionId) {
        return emissionRepository.findById(emissionId).orElse(null);
    }

    public Emission saveEmission(Emission emission) {
        return emissionRepository.save(emission);
    }

    public void deleteEmission(Long emissionId) {
        emissionRepository.deleteById(emissionId);
    }

	public Emission addEmission(Emission emission) {
		// TODO Auto-generated method stub
		return null;
	}

	public Emission updateEmission(Long id, Emission emission) {
		// TODO Auto-generated method stub
		return null;
	}
}
