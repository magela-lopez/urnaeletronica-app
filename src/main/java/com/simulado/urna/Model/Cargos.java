package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Cargos")
public class Cargos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "Nome")
    private String nome;

    @NotNull
    @Column(name = "TurnosPossiveis")
    private int turnosPossiveis;

    @OneToMany(mappedBy = "cargos")
    @JsonIgnore
    private List<Candidatos> candidatos;

}
