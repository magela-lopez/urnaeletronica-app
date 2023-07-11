package com.simulado.urna.Controller;

import com.simulado.urna.Service.PartidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partidos")
public class PartidosController {

    @Autowired
    PartidosService partidosService;

    @GetMapping
    public ResponseEntity<?> getPartidos(){
        return new ResponseEntity<>(partidosService.listarPartidos(), HttpStatus.OK);
    }
}
