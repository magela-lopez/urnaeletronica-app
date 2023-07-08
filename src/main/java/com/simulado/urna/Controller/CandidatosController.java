package com.simulado.urna.Controller;

import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Cargos;
import com.simulado.urna.Service.CandidatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatosController {

    @Autowired
    CandidatosService candidatosService;

    @GetMapping()
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(candidatosService.listarTodosCandidatos(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/findbypartido/{nomepartido}")
    public ResponseEntity<?>listByPartido(@PathVariable String nomepartido){
        List<Candidatos> candidatosList = candidatosService.listarCandidatosPorPartido(nomepartido);
        return new ResponseEntity<>(candidatosList, HttpStatus.OK);
    }

    @GetMapping(path = "/findbycargo/{nomecargo}")
    public ResponseEntity<?>listByCargo(@PathVariable String nomecargo) throws ClassNotFoundException {
        List<Candidatos> candidatosList = candidatosService.listarCandidatosPorCargo(nomecargo);
        return new ResponseEntity<>(candidatosList, HttpStatus.OK);
    }

    @GetMapping(path = "/findbyname/{nome}")
    public ResponseEntity<?>listByName(@PathVariable String nome){
        List<Candidatos> candidatosList = candidatosService.listarCandidatosPorNome(nome);
        return new ResponseEntity<>(candidatosList, HttpStatus.OK);
    }

    @GetMapping(path = "/getByNumber/{numeroEleitoral}")
    public ResponseEntity<?>getByNumber(@PathVariable String numeroEleitoral){
       List<Candidatos> candidatos = candidatosService.buscarCandidatosPorNumero(numeroEleitoral);
        return new ResponseEntity<>(candidatos, HttpStatus.OK);
    }
}
