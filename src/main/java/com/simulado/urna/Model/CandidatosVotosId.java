package com.simulado.urna.Model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;

@EqualsAndHashCode
@ToString
public class CandidatosVotosId {

    @Column(name = "Votos_id")
    private Long idVotos;

    @Column(name = "Candidatos_id")
    private Long idCandidato;

}
