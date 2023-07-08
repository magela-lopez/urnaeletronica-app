package com.simulado.urna.Controller;

import com.simulado.urna.Model.Eleicao;
import com.simulado.urna.Model.DTO.EleicaoDTO;
import com.simulado.urna.Service.EleicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/eleicao")
public class EleicaoController {

    @Autowired
    EleicaoService eleicaoService;

    @PostMapping
    public ResponseEntity<?> insertNovoTurno(@RequestBody EleicaoDTO eleicaoDTO){
        Eleicao eleicao = new Eleicao();
        eleicao.setVotosHabilitados(eleicaoDTO.getVotosHabilitados());
        eleicao.setTipo(eleicaoDTO.getTipo());
        eleicao.setData(eleicaoDTO.getData());
        return new ResponseEntity<>(eleicaoService.salvarNovoTurno(eleicao),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?>getResultsEleicao(){
        return new ResponseEntity<>(eleicaoService.consultarResultadoEleicoes(), HttpStatus.OK);
    }
}
