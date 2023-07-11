package com.simulado.urna.Controller;

import com.simulado.urna.Service.EleicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eleicao")
public class EleicaoController {

    @Autowired
    EleicaoService eleicaoService;

    @GetMapping
    public ResponseEntity<?>getResultsEleicao(){
        return new ResponseEntity<>(eleicaoService.consultarResultadoEleicoes(), HttpStatus.OK);
    }
}
