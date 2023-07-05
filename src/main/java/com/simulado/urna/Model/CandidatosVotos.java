package com.simulado.urna.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name = "CandidatosVotos")
public class CandidatosVotos {

    @EmbeddedId
    private CandidatosVotosId id;

    @ManyToOne
    @MapsId(value = "candidatosId")
    @JoinColumn(name = "Candidatos_id", nullable = false)
    private Candidatos candidato;

    @ManyToOne
    @MapsId(value = "votosId")
    @JoinColumn(name = "Votos_id", nullable = false)
    private Votos voto;

    @ManyToOne
    @MapsId(value = "eleicaoId")
    @JoinColumn(name = "Eleicao_id", nullable = false)
    private Eleicao eleicao;

}

//
//@ToString
//@Getter
//@Setter
//@Entity
//@Table(name = "CandidatosVotos")
//public class CandidatosVotos {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToMany
//    @JoinTable(name = "CandidatosVotos",
//            joinColumns = @JoinColumn(name = "eleicao_id"),
//            inverseJoinColumns = @JoinColumn(name = "eleicao_id"))
//    private Set<Eleicao> eleicao_id;
//
//    @ManyToMany
//    @JoinTable(name = "CandidatosVotos",
//            joinColumns = @JoinColumn(name = "candidatos_id"),
//            inverseJoinColumns = @JoinColumn(name = "candidatos_id"))
//    private Set<Candidatos> candidato_id;
//
//    @ManyToMany
//    @JoinTable(name = "CandidatosVotos",
//            joinColumns = @JoinColumn(name = "votos_id"),
//            inverseJoinColumns = @JoinColumn(name = "votos_id"))
//    private Set<Votos> votos_id;
//
//}
