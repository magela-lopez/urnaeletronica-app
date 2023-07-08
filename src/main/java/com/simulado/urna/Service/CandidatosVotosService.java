package com.simulado.urna.Service;

import com.simulado.urna.Enum.VotoEnum;
import com.simulado.urna.Model.*;
import com.simulado.urna.Model.DTO.ResultadoPorCandidatosDTO;
import com.simulado.urna.Model.DTO.ResultadoVotacaoDTO;
import com.simulado.urna.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CandidatosVotosService {

    //Repositories
    @Autowired
    private CandidatosRepository candidatosRepository;

    @Autowired
    private CandidatosVotosRepository candidatosVotosRepository;

    @Autowired
    private EleicaoRepository eleicaoRepository;

    @Autowired
    private PartidosRepository partidosRepository;

    @Autowired
    private CargosRepository cargosRepository;

    VotoEnum votoEnum;

    //********************************//

    //MÉTODOS ASSOCIADOS AO CONTROLLER

    public ResponseEntity<?>inserirVotos(){

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        int ultimaEleicaoRegistrada = eleicaoList.size()-1;

        LocalDate dataAtual = LocalDate.now();

        if(eleicaoList.isEmpty() || eleicaoList.get(ultimaEleicaoRegistrada).getData().isBefore(dataAtual)){
            return new ResponseEntity<>("A votação ainda não esta habilitada, espere a que seja ingressada as informacoes atualizadas", HttpStatus.BAD_REQUEST);
        }else{
            Eleicao eleicao = eleicaoRepository.findByTipo(eleicaoList.get(ultimaEleicaoRegistrada).getTipo());
            if(eleicao.getTipo().equalsIgnoreCase("Primeiro Turno")){
                inserirVotoPresidente(eleicao);
                inserirVotoGovernador(eleicao);
                inserirVotoSenador(eleicao);
                inserirVotoDeputadoFederal(eleicao);
                inserirVotoDeputadoEstadual(eleicao);
            }else{
                //VERIFICAR SE HÁ UM CANDIDATO ELEITO, SE NÃO HOUVER PARA NENHUM DOS DOIS CHAMAR OS DOIS MÉTODOS E FAZER A VOTAÇÃO SÓ PARA OS DOIS MAIS VOTADOS
                //CASO JÁ HOUVER ALGUM ELEITO CHAMAR SÓ UM MÉTODO
                inserirVotoPresidente(eleicao);
                inserirVotoGovernador(eleicao);
            }

        }

        return new ResponseEntity<>("Votacao Finalizada",HttpStatus.OK);
    }

    public ResultadoVotacaoDTO contagemVotosPorCategoria() {

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        int ultimaEleicaoRegistrada = eleicaoList.size()-1;
        Eleicao eleicao = eleicaoRepository.findByTipo(eleicaoList.get(ultimaEleicaoRegistrada).getTipo());


        Long votosValidos = candidatosVotosRepository.countCandidatosVotosByVoto(VotoEnum.VALIDO);
        Long votosBranco= candidatosVotosRepository.countCandidatosVotosByVoto(VotoEnum.BRANCO);
        Long votosNulo= candidatosVotosRepository.countCandidatosVotosByVoto(VotoEnum.NULO);

        Long votosTotais = votosValidos + votosNulo + votosBranco;

        eleicao.setVotosTotaisNulos(votosNulo);
        eleicao.setVotosTotaisBrancos(votosBranco);
        eleicao.setVotosTotaisValidos(votosValidos);
        eleicao.setVotosTotais(votosTotais);
        eleicaoRepository.save(eleicao);

        return new ResultadoVotacaoDTO(votosValidos, votosBranco, votosNulo);
    }

    public ResponseEntity<?>contagemVotosPorCandidato(String nomeCandidato){

        Candidatos candidato = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(nomeCandidato);
        Long votosValidosCandidatos = candidatosVotosRepository.countCandidatosVotosByCandidatoAndVoto(candidato, VotoEnum.VALIDO);

        String textoResultado = "Votos totais validos para o candidato "+candidato.getNome()+ ": "+votosValidosCandidatos;
        return new ResponseEntity<>(textoResultado, HttpStatus.OK);
    }

    public ResponseEntity<?>candidatoEleitoPorCargo(String nomeCargo){

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        int ultimaEleicaoRegistrada = eleicaoList.size()-1;
        Eleicao eleicao = eleicaoList.get(ultimaEleicaoRegistrada);
        Cargos cargo = cargosRepository.findCargosByNomeContainsIgnoreCase(nomeCargo);

        if(cargo.getTurnosPossiveis() ==2){
            return new ResponseEntity<>(eleicaoPresidenteGovernador(cargo),HttpStatus.OK); }
        else{
            ResultadoPorCandidatosDTO candidatoEleito = candidatosVotosRepository.findCandidatoEleitoPorCargo(cargo.getId());
            String textoCandidatoEleito = "O "+cargo.getNome()+ " eleito foi "+ candidatoEleito.getNome()
                    + " com "+ candidatoEleito.getTotalVotos()+" votos totais validos";

            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> eleicaoPresidenteGovernador(Cargos cargos) throws NumberFormatException{

        List<Eleicao> eleicaoList = eleicaoRepository.findAll();
        int ultimaEleicaoRegistrada = eleicaoList.size()-1;
        Eleicao eleicao = eleicaoList.get(ultimaEleicaoRegistrada);

        ResultadoPorCandidatosDTO candidatoEleito = candidatosVotosRepository.findCandidatoEleitoPorCargoAndTurno(cargos.getId(), eleicao.getId() );   //Retorna o candidato com maior numero de votos

        Long totalVotos = candidatosVotosRepository.countCandidatosVotosByCargoAndVotoValido(cargos.getId());         //Retorna o total de votos validos para todos os candidatos para o cargo de presidente ou governador

        Double porcentagemEleitoral = 0.51 * totalVotos;        //É definido a quantidade de votos que o candidato deve ter para poder ser eleito, dependendo de quantos votos validos totais houveram entre todos os candidatos

        Long totalVotoCandidato =  candidatoEleito.getTotalVotos();
        String textoCandidatoEleito = "O candidato eleito a "+ cargos.getNome() + " foi " + candidatoEleito.getNome() +
                " com " + candidatoEleito.getTotalVotos() +" votos totais validos";

        if( totalVotoCandidato > porcentagemEleitoral){
            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
        else if( totalVotoCandidato < porcentagemEleitoral & eleicao.getTipo().equalsIgnoreCase("Primeiro turno") ){
            eleicao.setIsSegundoTurnoNecessário(true);
            eleicaoRepository.save(eleicao);
            return new ResponseEntity<>("Será necessário um segundo turno, nenhum candidato " +
                    "alcançou a porcentagem necessária", HttpStatus.OK);
        }else if(totalVotoCandidato < porcentagemEleitoral & eleicao.getTipo().equalsIgnoreCase("Segundo turno")){
            eleicao.setIsSegundoTurnoNecessário(false);
            eleicaoRepository.save(eleicao);
            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




    //MÉTODOS GERAIS
    public void inserirVotoPresidente(Eleicao eleicao){

        Random random = new Random();

            for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {

                CandidatosVotos candidatosVotos = new CandidatosVotos();

                int numRandom = random.nextInt(7) + 1;
                Long numPresidente = (long) numRandom;

                Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

                int opcVoto = random.nextInt(3)+1;

                votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

                candidatosVotos.setCandidato(candidato.orElse(null));
                candidatosVotos.setEleicao(eleicao);
                candidatosVotos.setVoto(votoEnum);
                candidatosVotosRepository.save(candidatosVotos);
            }
            //inserirVotoGovernador(eleicao);
    }

    public void inserirVotoGovernador(Eleicao eleicao){
        Random random = new Random();

            for (int i = 0; i <= eleicao.getVotosHabilitados(); i++) {
                CandidatosVotos candidatosVotos = new CandidatosVotos();

                int numRandom = random.nextInt(10) + 8;
                int opcVoto = random.nextInt(3)+1;
                Long numPresidente = (long) numRandom;

                Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

                votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

                candidatosVotos.setCandidato(candidato.orElse(null));
                candidatosVotos.setEleicao(eleicao);
                candidatosVotos.setVoto(votoEnum);
                candidatosVotosRepository.save(candidatosVotos);
            }

//            inserirVotoSenador(eleicao);
    }

    public void inserirVotoSenador(Eleicao eleicao){

        Random random = new Random();

            for (int i = 0; i <=eleicao.getVotosHabilitados(); i++) {
                CandidatosVotos candidatosVotos = new CandidatosVotos();

                int numRandom = random.nextInt(8) + 18;
                int opcVoto = random.nextInt(3)+1;
                Long numPresidente = (long) numRandom;

                Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

                votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

                candidatosVotos.setCandidato(candidato.orElse(null));
                candidatosVotos.setEleicao(eleicao);
                candidatosVotos.setVoto(votoEnum);
                candidatosVotosRepository.save(candidatosVotos);
            }
            //inserirVotoDeputadoFederal(eleicao);
    }

    public void inserirVotoDeputadoFederal(Eleicao eleicao){

        Random random = new Random();

            for (int i = 0; i <= eleicao.getVotosHabilitados(); i++) {
                CandidatosVotos candidatosVotos = new CandidatosVotos();

                int numRandom = random.nextInt(5) + 26;
                int opcVoto = random.nextInt(3)+1;
                Long numPresidente = (long) numRandom;

                Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

                votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

                candidatosVotos.setCandidato(candidato.orElse(null));
                candidatosVotos.setEleicao(eleicao);
                candidatosVotos.setVoto(votoEnum);
                candidatosVotosRepository.save(candidatosVotos);
            }

           // inserirVotoDeputadoEstadual(eleicao);
        //return "Votacao Completada";
    }

    public void inserirVotoDeputadoEstadual(Eleicao eleicao){

        Random random = new Random();

            for (int i = 0; i <= eleicao.getVotosHabilitados(); i++) {
                CandidatosVotos candidatosVotos = new CandidatosVotos();

                int numRandom = random.nextInt(9) + 31;
                int opcVoto = random.nextInt(3)+1;
                Long numPresidente = (long) numRandom;

                Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

                votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

                candidatosVotos.setCandidato(candidato.orElse(null));
                candidatosVotos.setEleicao(eleicao);
                candidatosVotos.setVoto(votoEnum);
                candidatosVotosRepository.save(candidatosVotos);
            }
    }


}
