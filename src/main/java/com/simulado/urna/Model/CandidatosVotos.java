package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simulado.urna.Enum.VotoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "CandidatosVotos")
public class CandidatosVotos {

//    @EmbeddedId
//    private CandidatosVotosId id; No nos sirve pues se va a generar muchas entradas para un mismo idCandidatos y idEleicao, y los id's deben ser únicos usando el @EmbeddedId

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CandidatosId")
    @JsonIgnore
    private Candidatos candidato;

    @ManyToOne
    @JoinColumn(name = "EleicaoId")
    @JsonIgnore
    private Eleicao eleicao;

    @Column(name = "Voto")
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;

}

