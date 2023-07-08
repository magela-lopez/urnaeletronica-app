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

    @GetMapping(path = "/resultado")
    public ResponseEntity<?> getResultadoVotacao(){
        return new ResponseEntity<>(candidatosVotosService.contagemVotosPorCategoria().toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/resultadoCandidato/{candidato}")
    public ResponseEntity<?> getResultadoVotacaoPorCandidato(@PathVariable String candidato){
        return new ResponseEntity<>(candidatosVotosService.contagemVotosPorCandidato(candidato), HttpStatus.OK);
    }

    @GetMapping(path = "/resultadoCargo/{cargo}")
    public ResponseEntity<?>getResultadoPorCargo(@PathVariable String cargo){
        return new ResponseEntity<>(candidatosVotosService.candidatoEleitoPorCargo(cargo), HttpStatus.OK);
    }

}
