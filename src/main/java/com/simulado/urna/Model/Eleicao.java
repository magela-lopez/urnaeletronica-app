package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Eleicao")
public class Eleicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "Tipo")
    private String tipo;

    @NotNull
    @Column(name = "Votos_Totais")
    private Long votosTotais;

    @NotNull
    @Column(name = "Votos_Habilitados")
    private Long votosEsperados;

    @OneToMany(mappedBy = "eleicao")
    private List<CandidatosVotos> votosCandidato;


}
