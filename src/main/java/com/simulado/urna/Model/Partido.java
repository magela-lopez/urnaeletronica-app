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
@Table(name = "Partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "Nome_Partido")
    @NotNull
    private String nome;

    @Column(name = "Numero_Eleitoral")
    @NotNull
    private String numeroEleitoral;

    @OneToMany(mappedBy = "partido")
    private List<Candidato> candidatos;


}
