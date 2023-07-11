package com.simulado.urna.Service;

import com.simulado.urna.Model.Eleicao;
import com.simulado.urna.Repository.CandidatosVotosRepository;
import com.simulado.urna.Repository.EleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleicaoService {

    @Autowired
    EleicaoRepository eleicaoRepository;

    @Autowired
    CandidatosVotosRepository candidatosVotosRepository;

    public List<Eleicao> consultarResultadoEleicoes(){
        return eleicaoRepository.findAll();
    }


}
