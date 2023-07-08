package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@ToString(exclude = "votos")
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
    @Column(name = "Data")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate data;


    @Column(name = "Votos_Totais")
    private Long votosTotais;

    @Column(name = "Votos_Totais_Validos")
    private Long votosTotaisValidos;

    @Column(name = "Votos_Totais_em_Branco")
    private Long votosTotaisBrancos;

    @Column(name = "Votos_Totais_Nulo")
    private Long votosTotaisNulos;

    @NotNull
    @Column(name = "Votos_Habilitados")
    private Long votosHabilitados;

    @Column(name = "Segundo_Necesario")
    private Boolean isSegundoTurnoNecessário;

    @OneToMany(mappedBy = "eleicao")
    @JsonIgnore
    private Set<CandidatosVotos> votos;



}
