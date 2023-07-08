package com.simulado.urna.Model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class ResultadoVotacaoDTO {

    private Long votosValidos;
    private Long votosBranco;
    private Long votosNulo;

    public ResultadoVotacaoDTO(Long votosValidos, Long votosBranco, Long votosNulo) {
        this.votosValidos = votosValidos;
        this.votosBranco = votosBranco;
        this.votosNulo = votosNulo;
    }

    @Override
    public String toString() {
        return "Resultado das eleicoes{" +
                "Votos Validos=" + votosValidos +
                ", Votos em branco=" + votosBranco +
                ", Votos nulos=" + votosNulo + '\'' +
                '}';
    }
}
