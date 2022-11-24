package com.webavanzado.demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.webavanzado.demo.dto.EstudianteDTO;
import com.webavanzado.demo.dto.NuevoEstudianteDTO;

import com.webavanzado.demo.services.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService service;
    @Autowired
    public EstudianteController(EstudianteService srv){
        this.service=srv;
    }
    @PostMapping()
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody NuevoEstudianteDTO estudianteDTO){
        EstudianteDTO result = service.create(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> retrive(@PathVariable("id") Long id){
        EstudianteDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    @GetMapping() //el verbo es diferente a create ya que va
    public ResponseEntity<List<EstudianteDTO>> list(){
        List<EstudianteDTO> result  = service.list();
        return ResponseEntity.ok().body(result);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@RequestBody EstudianteDTO arbitroDTO, @PathVariable("id") Long id){
        EstudianteDTO result = service.update(arbitroDTO, id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("estudiante deleted!");        
    }
}
