package com.simulado.urna.Repository;

import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Cargos;
import com.simulado.urna.Model.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidatos, Long> {

    List<Candidatos> findCandidatosByCargos(Cargos cargos);
    List<Candidatos> findCandidatosByNomeContains (String nome);
    List<Candidatos> findCandidatosByPartido(Partidos nomePartido);
    List<Candidatos> findCandidatosByNumero(String numero);
    Candidatos findCandidatosByNomeContainsIgnoreCase(String nome);
    Optional<Candidatos> findById (Long id);
}
