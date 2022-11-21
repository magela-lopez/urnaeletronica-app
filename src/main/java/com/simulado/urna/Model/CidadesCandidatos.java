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
@Table(name = "Cidades")
public class CidadesCandidatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "Nome")
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "Zona_Eleitoral_id")
    private ZonaEleitoral zonaEleitoral;

    @OneToMany(mappedBy = "cidadesCandidatos")
    private List<Candidato> candidatos;
}
