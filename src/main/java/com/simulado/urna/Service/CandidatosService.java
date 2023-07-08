package com.simulado.urna.Service;

import com.simulado.urna.Model.Candidatos;
import com.simulado.urna.Model.Cargos;
import com.simulado.urna.Model.Partidos;
import com.simulado.urna.Repository.CandidatosRepository;
import com.simulado.urna.Repository.CargosRepository;
import com.simulado.urna.Repository.PartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CandidatosService {

    @Autowired
    CandidatosRepository candidatosRepository;

    @Autowired
    CargosRepository cargosRepository;

    @Autowired
    PartidosRepository partidosRepository;

    public Page<Candidatos> listarTodosCandidatos(Pageable pageable){
        return candidatosRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    public List<Candidatos> listarCandidatosPorPartido (String nome){
        Partidos partido = partidosRepository.findByNomePartido(nome); //Fazer a busca primeiro de um objeto Partido com o repository do Partido. Funciona pois somente um objeto possui esse nome
        List<Candidatos> candidatosListPartido = candidatosRepository.findCandidatosByPartido(partido); //Fazer a busca do candidato passando o partido encontrado
        if(candidatosListPartido.isEmpty())
            System.out.println("Nenhum candidato encontrado");
        return candidatosListPartido;
    }

    public List<Candidatos> listarCandidatosPorCargo (String cargo) throws ClassNotFoundException {

        Cargos cargos = cargosRepository.findCargosByNomeContainsIgnoreCase(cargo);

        List<Candidatos> candidatosListCargo = candidatosRepository.findCandidatosByCargos(cargos);
        if(candidatosListCargo.isEmpty())
            throw new ClassNotFoundException("Nenhum candidato encontrado");
        return candidatosListCargo;
    }

    public List<Candidatos> listarCandidatosPorNome (String nome){
        List<Candidatos> candidatosListNome = candidatosRepository.findCandidatosByNomeContains(nome);
        if(candidatosListNome.isEmpty())
            System.out.println("Nenhum candidato encontrado");
        return candidatosListNome;
    }

    public List<Candidatos> buscarCandidatosPorNumero (String numero){
        List<Candidatos> candidatos = candidatosRepository.findCandidatosByNumero(numero);
        if(candidatos.isEmpty())
            System.out.println("Nenhum candidato encontrado");
        return candidatos;
    }



}
