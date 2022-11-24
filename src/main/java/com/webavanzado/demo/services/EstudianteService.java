package com.webavanzado.demo.services;

import java.util.List;

import com.webavanzado.demo.dto.EstudianteDTO;
import com.webavanzado.demo.dto.NuevoEstudianteDTO;

public interface EstudianteService {
    
    public EstudianteDTO create (NuevoEstudianteDTO estudianteDTO);
    public EstudianteDTO retrieve (Long id);
    public EstudianteDTO update(EstudianteDTO estudianteDTO, Long id);
    public void delete(Long id);

    public List<EstudianteDTO> list();
}
