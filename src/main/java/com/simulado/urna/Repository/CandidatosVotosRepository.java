package com.simulado.urna.Repository;

import com.simulado.urna.Enum.VotoEnum;
import com.simulado.urna.Model.*;
import com.simulado.urna.Model.DTO.ResultadoPorCandidatosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatosVotosRepository extends JpaRepository<CandidatosVotos, Long> {

    @Query(value = "SELECT SUM(TotalVotos) AS TotalVotos\n" +
            "FROM(\n" +
            "\tSELECT COUNT(candidatosvotos.Voto) AS TotalVotos\n" +
            "\tFROM CandidatosVotos\n" +
            "\tJOIN Candidatos ON candidatosvotos.CandidatosId = candidatos.id\n" +
            "\tWHERE candidatos.Cargos_Id = ?1 AND candidatosvotos.Voto = 'VALIDO' AND candidatosvotos.EleicaoId = ?2 " +
            "\tGROUP BY candidatos.Nome order by TotalVotos DESC) subTotal", nativeQuery = true)
    Long countVotosByCargoAndVotoValidoAndEleicao(Long idCargo, Long eleicaoId);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos " +
            "FROM candidatosvotos " +
            "JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id " +
            "WHERE candidatos.Cargos_Id = ?1 AND candidatosvotos.Voto = 'VALIDO' AND candidatosvotos.EleicaoId = ?2 " +
            "GROUP BY Candidatos.nome ORDER BY TotalVotos DESC", nativeQuery = true)
    List<ResultadoPorCandidatosDTO> findCandidatosVotosByCargoAndVotoAndEleicao(Long idCargo, Long eleicaoId);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos " +
            "FROM candidatosvotos " +
            "JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id " +
            "WHERE candidatos.Cargos_Id = ?1 AND candidatosvotos.Voto = 'VALIDO' AND candidatosvotos.EleicaoId = ?2 " +
            "GROUP BY Candidatos.nome ORDER BY TotalVotos DESC LIMIT 1", nativeQuery = true)
    ResultadoPorCandidatosDTO findCandidatoEleitoPorCargoAndEleicao(Long idCargo, Long idEleicao);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos \n" +
            "            FROM candidatosvotos \n" +
            "            JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id\n" +
            "            WHERE candidatos.Cargos_Id = ? AND candidatosvotos.Voto = 'VALIDO'" +
            "            GROUP BY Candidatos.nome ORDER BY TotalVotos DESC LIMIT 1", nativeQuery = true)
    ResultadoPorCandidatosDTO findCandidatoEleitoPorCargo(Long idCargo); //É preciso pois é melhor utilizar este método para os 3 cargos que não possuem 2 turno

    Long countCandidatosVotosByCandidatoAndVotoAndEleicao(Candidatos candidatos, VotoEnum votoEnum, Eleicao eleicao);

    Long countCandidatosVotosByVotoAndEleicao(VotoEnum votoEnum, Eleicao eleicao);

    Integer countCandidatosVotosByCandidatoAndEleicao(Candidatos candidatos, Eleicao eleicao);

}
