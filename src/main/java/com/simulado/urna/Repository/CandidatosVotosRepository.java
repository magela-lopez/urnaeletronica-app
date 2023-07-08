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
            "\tWHERE candidatos.Cargos_Id = ? AND candidatosvotos.Voto = 'VALIDO'\n" +
            "\tGROUP BY candidatos.Nome order by TotalVotos DESC) subTotal", nativeQuery = true)
    Long countCandidatosVotosByCargoAndVotoValido(Long id);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos " +
            "FROM candidatosvotos " +
            "JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id " +
            "WHERE candidatos.Cargos_Id = ? AND candidatosvotos.Voto = 'VALIDO' " +
            "GROUP BY Candidatos.nome ORDER BY TotalVotos DESC ", nativeQuery = true)
    List<ResultadoPorCandidatosDTO> countCandidatosVotosByCargoAndVoto(Long id);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos \n" +
            "            FROM candidatosvotos \n" +
            "            JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id\n" +
            "            WHERE candidatos.Cargos_Id = ? AND candidatosvotos.Voto = 'VALIDO' AND EleicaoId = ?" +
            "            GROUP BY Candidatos.nome ORDER BY TotalVotos DESC LIMIT 1", nativeQuery = true)
    ResultadoPorCandidatosDTO findCandidatoEleitoPorCargoAndTurno(Long idCargo, Long idEleicao);

    @Query(value = "SELECT candidatos.Nome, COUNT(candidatosvotos.Voto) AS TotalVotos \n" +
            "            FROM candidatosvotos \n" +
            "            JOIN candidatos ON candidatosvotos.CandidatosId = candidatos.id\n" +
            "            WHERE candidatos.Cargos_Id = ? AND candidatosvotos.Voto = 'VALIDO'" +
            "            GROUP BY Candidatos.nome ORDER BY TotalVotos DESC LIMIT 1", nativeQuery = true)
    ResultadoPorCandidatosDTO findCandidatoEleitoPorCargo(Long idCargo);


    Long countCandidatosVotosByCandidatoAndVoto(Candidatos candidatos, VotoEnum votoEnum);

    Long countCandidatosVotosByVoto(VotoEnum votoEnum);

}
