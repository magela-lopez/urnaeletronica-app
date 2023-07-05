package com.simulado.urna.Model;


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

    @ManyToOne()
    @JoinColumn(name = "Partidos_id")
    @NotNull
    private Partidos partido;

    @ManyToOne()
    @JoinColumn(name = "Cargos_id")
    @NotNull
    private Cargos cargos;

//    @ManyToMany(mappedBy = "candidato_id")
//    private Set<CandidatosVotos> votosCandidatos;


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
