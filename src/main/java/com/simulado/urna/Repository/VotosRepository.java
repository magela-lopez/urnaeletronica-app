package com.simulado.urna.Repository;

import com.simulado.urna.Model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotosRepository extends JpaRepository<Votos, Long> {
}
