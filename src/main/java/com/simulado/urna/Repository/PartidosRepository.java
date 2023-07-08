package com.simulado.urna.Repository;

import com.simulado.urna.Model.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidosRepository extends JpaRepository<Partidos, Long> {

    Partidos findByNomePartido(String nome);
    List<Partidos> findByCandidatos(String nome);
    List<Partidos> findByNumeroEleitoral(String numero);
    Partidos findByNumeroEleitoralAndCandidatos(String numeroEleitoral, String nomeCandidato);
}
