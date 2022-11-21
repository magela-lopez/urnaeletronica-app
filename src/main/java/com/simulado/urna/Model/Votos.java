package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Votos")
public class Votos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "Tipo")
    private String tipo;

    @OneToMany(mappedBy = "votos")
    private List<CandidatosVotos> votos = new ArrayList<>();

    /*
    @ManyToMany(mappedBy = "votos")
    public List<Candidato> candidatosList = new ArrayList<>();*/
}
