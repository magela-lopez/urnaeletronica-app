package com.simulado.urna.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidatosVotosId implements Serializable {

    @Column(name = "Candidatos_id")
    private Integer candidatosId;

    @Column(name = "Votos_id")
    private Integer votosId;

    @Column(name = "Eleicao_id")
    private Integer eleicaoId;

    // Construtores, getters e setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatosVotosId that = (CandidatosVotosId) o;
        return Objects.equals(candidatosId, that.candidatosId) &&
                Objects.equals(votosId, that.votosId) &&
                Objects.equals(eleicaoId, that.eleicaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidatosId, votosId, eleicaoId);
    }
}
