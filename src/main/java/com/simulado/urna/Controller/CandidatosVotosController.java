package com.simulado.urna.Controller;

import com.simulado.urna.Service.CandidatosVotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votosCandidatos")
public class CandidatosVotosController {

    @Autowired
    CandidatosVotosService candidatosVotosService;


    @PostMapping//Inserir o voto sem precisar passar nenhum parametro, só com o botão executar
    public ResponseEntity<?> insertVotos(){
        return new ResponseEntity<>(candidatosVotosService.inserirVotos(), HttpStatus.OK);
    }

    @GetMapping(path = "/resultado/{eleicao}")
    public ResponseEntity<?> getResultadoVotacao(@PathVariable String eleicao){
        return new ResponseEntity<>(candidatosVotosService.contagemVotosPorCategoria(eleicao).toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/resultadoCandidato/{candidato}")
    public ResponseEntity<?> getResultadoVotacaoPorCandidato(@PathVariable String candidato, String eleicao){
        return new ResponseEntity<>(candidatosVotosService.contagemVotosPorCandidato(candidato, eleicao), HttpStatus.OK);
    }

    @GetMapping(path = "/resultadoCargo/{cargo}/{eleicao}")
    public ResponseEntity<?>getResultadoPorCargo(@PathVariable String cargo, @PathVariable String eleicao){
        return new ResponseEntity<>(candidatosVotosService.candidatoEleitoPorCargo(cargo, eleicao), HttpStatus.OK);
    }

}
