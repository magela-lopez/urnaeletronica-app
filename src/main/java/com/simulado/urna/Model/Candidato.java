package com.simulado.urna.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;

import com.simulado.urna.Model.Partido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Candidatos")
public class Candidato {

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

    @Column(name = "Total_Votos")
    private String total_votos;

    @Column(name = "Vice")
    private String vice;

    @Column(name = "Suplente_1")
    private String suplente1;

    @Column(name = "Suplente_2")
    private String suplente2;

    @ManyToOne()
    @JoinColumn(name = "Partidos_id")
    private Partido partido;

    @ManyToOne()
    @JoinColumn(name = "Cargos_id")
    private Cargos cargos;

    //Verificar relacion
    @ManyToOne()
    @JoinColumn(name = "Cidade_id")
    private CidadesCandidatos cidadesCandidatos;

    @ManyToOne()
    @JoinColumn(name = "Zona_Eleitoral_id")
    private ZonaEleitoral zonasEleitorais;
    //

    @OneToMany(mappedBy = "candidato")
    private List<CandidatosVotos> votosCandidatos = new ArrayList<>();



    /*@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "candidatosvotos",
            joinColumns = @JoinColumn(name = "candidatos_id"),
            inverseJoinColumns = @JoinColumn(name = "Votos_id")
    )
    private List<Votos> votos = new ArrayList<>();*/

}
