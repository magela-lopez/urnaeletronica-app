package com.simulado.urna.Model;

import javax.persistence.*;

@Entity
@Table(name = "CandidatosVotos")
public class CandidatosVotos {

    @Id
    private Long idCandidato;

    @ManyToOne()
    @JoinColumn(name = "Eleicao_id")
    private Eleicao eleicao;

    @ManyToOne
    @JoinColumn(name = "Candidatos_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "Votos_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Votos votos;


}
