package com.simulado.urna.Repository;

import com.simulado.urna.Model.CandidatosVotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatosVotosRepository extends JpaRepository<CandidatosVotos, Long> {
}
