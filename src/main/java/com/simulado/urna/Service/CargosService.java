package com.simulado.urna.Service;

import com.simulado.urna.Model.Cargos;
import com.simulado.urna.Model.Partidos;
import com.simulado.urna.Repository.CargosRepository;
import com.simulado.urna.Repository.PartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargosService {

    @Autowired
    CargosRepository cargosRepository;

    public List<Cargos> listarCargos(){
        return cargosRepository.findAll();
    }
}
