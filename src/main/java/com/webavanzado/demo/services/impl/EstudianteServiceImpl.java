package com.webavanzado.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webavanzado.demo.dto.EstudianteDTO;
import com.webavanzado.demo.dto.NuevoEstudianteDTO;
import com.webavanzado.demo.exceptions.ResourceNotFoundException;
import com.webavanzado.demo.models.Estudiante;
import com.webavanzado.demo.repositories.EstudianteRepository;
import com.webavanzado.demo.services.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    final ModelMapper modelMapper;
    final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(@Autowired EstudianteRepository repository, ModelMapper mapper){
        this.estudianteRepository = repository;
        this.modelMapper = mapper;
    }

    //Crear
    @Override
    @Transactional
    public EstudianteDTO create(NuevoEstudianteDTO estudianteDTO) {
        Estudiante estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        estudianteRepository.save(estudiante);
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    //Consultar
    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO retrieve(Long id){
        Estudiante estudiante = estudianteRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Estudiante not found"));
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    //Actualizar
    @Override
    @Transactional
    public EstudianteDTO update(EstudianteDTO estudianteDTO, Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Estudiante not found"));
        estudiante.setId(id);
        estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        estudianteRepository.save(estudiante);
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }
    //eliminar
    @Override
    @Transactional
    public void delete(Long id){ 
        Estudiante estudiante = estudianteRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Estudiante not found"));

        estudianteRepository.deleteById(estudiante.getId());        
    }
    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> list() {

        List<Estudiante> arbitros = estudianteRepository.findAll();
        return arbitros.stream().map(estudiante -> modelMapper.map(estudiante, EstudianteDTO.class))
        .collect(Collectors.toList());
                  
    }



    
}
