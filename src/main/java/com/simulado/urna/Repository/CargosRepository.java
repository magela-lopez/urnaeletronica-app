package com.simulado.urna.Repository;

import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Cargos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargosRepository extends JpaRepository<Cargos, Long> {

    Cargos findCargosByNomeContainsIgnoreCase(String nome);
    List<Cargos> findCargosByCandidatos(String nome);
    Cargos findCargosById(Long id);
}
