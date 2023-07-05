package com.simulado.urna.Repository;

import com.simulado.urna.Model.Eleicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleicaoRepository extends JpaRepository<Eleicao, Long> {
}
