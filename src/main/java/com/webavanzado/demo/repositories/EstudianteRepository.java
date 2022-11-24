package com.webavanzado.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webavanzado.demo.models.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante,Long> {
    
}
