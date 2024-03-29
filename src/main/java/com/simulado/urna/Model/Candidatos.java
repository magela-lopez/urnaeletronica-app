package com.simulado.urna.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Candidatos")
public class Candidatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "Nome")
    @NotNull
    private String nome;

    @Column(name = "Numero")
    @NotNull
    private String numero;

    @Column(name = "Vice")
    private String vice;

    @Column(name = "Total_Votos")
    private Integer total_votos;

    @Column(name = "Eleito")
    @NotNull
    private Boolean eleito;

    @ManyToOne()
    @JoinColumn(name = "Partidos_id")
    @NotNull
    private Partidos partido;

    @ManyToOne()
    @JoinColumn(name = "Cargos_id")
    @NotNull
    private Cargos cargos;

    @OneToMany(mappedBy = "candidato")
    @JsonIgnore
    private Set<CandidatosVotos> votos;

}
