package com.simulado.urna.Service;

import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Eleicao;
import com.simulado.urna.Repository.CandidatosVotosRepository;
import com.simulado.urna.Repository.EleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EleicaoService {

    @Autowired
    EleicaoRepository eleicaoRepository;

    @Autowired
    CandidatosVotosRepository candidatosVotosRepository;


    public ResponseEntity<?> salvarNovoTurno(Eleicao eleicao){

        List<Eleicao> eleicoesList = eleicaoRepository.findAll();

        return new ResponseEntity<>(verificar(eleicoesList, eleicao), HttpStatus.OK);
    }

    public List<Eleicao> consultarResultadoEleicoes(){
        return eleicaoRepository.findAll();
    }


    public ResponseEntity<?> verificar(List<Eleicao> eleicoesList, Eleicao eleicao){

        int ultimoElemento = eleicoesList.size()-1;

        if(eleicoesList.isEmpty() & eleicao.getTipo().equalsIgnoreCase("Primeiro Turno") ){
            return new ResponseEntity<>(eleicaoRepository.save(eleicao), HttpStatus.OK);
        }
        else if(eleicoesList.isEmpty() & eleicao.getTipo().equalsIgnoreCase("Segundo Turno")){
            return new ResponseEntity<>("Deve ingressar primeiro o Primeiro Turno", HttpStatus.OK);
        }
        else if(!eleicoesList.isEmpty() & eleicao.getTipo().equalsIgnoreCase("Segundo Turno")
                & eleicoesList.get(ultimoElemento).getTipo().equalsIgnoreCase("Primeiro Turno")
                & eleicoesList.get(ultimoElemento).getIsSegundoTurnoNecessário()){

            if(eleicao.getData().isBefore(eleicoesList.get(ultimoElemento).getData())){
                return new ResponseEntity<>("A data nao pode ser antes que a data do Primeiro Turno", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(eleicaoRepository.save(eleicao), HttpStatus.OK);
            }
        }
        else if(!eleicoesList.isEmpty() & eleicao.getTipo().equalsIgnoreCase("Segundo Turno")
                & eleicoesList.get(ultimoElemento).getTipo().equalsIgnoreCase("Primeiro Turno")
                & !eleicoesList.get(ultimoElemento).getIsSegundoTurnoNecessário()){
            return new ResponseEntity<>("Não é necessario configurar o segundo turno, todos os candidatos já foram eleitos", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
