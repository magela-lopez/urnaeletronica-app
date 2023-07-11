package com.simulado.urna.Service;

import com.simulado.urna.Model.Eleicao;
import com.simulado.urna.Model.Partidos;
import com.simulado.urna.Repository.PartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidosService {

    @Autowired
    PartidosRepository partidosRepository;

    public List<Partidos> listarPartidos(){
        return partidosRepository.findAll();
    }
}
