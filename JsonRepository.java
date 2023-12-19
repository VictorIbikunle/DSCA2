package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Emission;
import com.example.demo.EmissionJson;

@Repository
public interface JsonRepository extends JpaRepository<EmissionJson, Long> {
}

