package com.simulado.urna.Repository;

import com.simulado.urna.Model.Candidatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidatos, Long> {
}
