package com.simulado.urna.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Eleicao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "CPF")
    private String CPF;

    @ManyToOne
    @JoinColumn(name = "idCandidatos")
    private Candidatos idCandidatoVoto;

    @ManyToOne
    @JoinColumn(name = "idEleicao")
    private Eleicao idEleicao;

}
