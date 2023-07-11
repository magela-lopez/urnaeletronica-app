package com.simulado.urna.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "candidatos")
@Getter
@Setter
@Entity
@Table(name = "Partidos")
public class Partidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "Nome_Partido")
    @NotNull
    private String nomePartido;

    @Column(name = "Numero_Eleitoral")
    @NotNull
    private String numeroEleitoral;

    @OneToMany(mappedBy = "partido")
    @JsonIgnore //Para não ter recursividade na hora de serializar o JSON e não ter erro no ListAll
    private List<Candidatos> candidatos;


}
