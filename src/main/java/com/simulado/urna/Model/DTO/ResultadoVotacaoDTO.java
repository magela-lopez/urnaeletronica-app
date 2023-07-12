package com.simulado.urna.Model.DTO;

import com.simulado.urna.Model.Candidatos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ResultadoVotacaoDTO {

    private Long votosValidos;
    private Long votosBranco;
    private Long votosNulo;
    private List<Candidatos> candidatosList;

    public ResultadoVotacaoDTO(Long votosValidos, Long votosBranco, Long votosNulo) {
        this.votosValidos = votosValidos;
        this.votosBranco = votosBranco;
        this.votosNulo = votosNulo;
    }

    public ResultadoVotacaoDTO(Long votosValidos, Long votosBranco, Long votosNulo, List<Candidatos> candidatosList) {
        this.votosValidos = votosValidos;
        this.votosBranco = votosBranco;
        this.votosNulo = votosNulo;
        this.candidatosList = candidatosList;
    }

    @Override
    public String toString() {
        return "ResultadoVotacaoDTO{" +
                "votosValidos=" + votosValidos +
                ", votosBranco=" + votosBranco +
                ", votosNulo=" + votosNulo +
                ", candidatosList=" + candidatosList +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Resultado das eleicoes{" +
//                "Votos Validos=" + votosValidos +
//                ", Votos em branco=" + votosBranco +
//                ", Votos nulos=" + votosNulo + '\'' +
//                '}';
//    }
}
