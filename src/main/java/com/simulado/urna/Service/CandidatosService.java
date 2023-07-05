package com.simulado.urna.Service;

import com.simulado.urna.Repository.CandidatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatosService {

    @Autowired
    CandidatosRepository candidatosRepository;

    @Autowired
    VotosService votosService;


}
