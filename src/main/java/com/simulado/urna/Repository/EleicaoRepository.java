package com.simulado.urna.Repository;

import com.simulado.urna.Model.Eleicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EleicaoRepository extends JpaRepository<Eleicao, Long> {

    Eleicao findByData(Date date);
    Eleicao findByTipo(String tipo);

}
