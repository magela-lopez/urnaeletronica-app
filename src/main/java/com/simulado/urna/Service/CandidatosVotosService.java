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
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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

        Eleicao eleicaoPrimeiroTurno = eleicaoRepository.findByTipo("Primeiro Turno");
        Eleicao eleicaoSegundoTurno = eleicaoRepository.findByTipo("Segundo Turno");
        LocalDate dataAtual = LocalDate.now();


        if(dataAtual.isBefore(eleicaoPrimeiroTurno.getData())){
            return new ResponseEntity<>("A votação ainda não esta habilitada, espere a que seja ingressada as informacoes atualizadas", HttpStatus.BAD_REQUEST);
        }else if(dataAtual.isAfter(eleicaoPrimeiroTurno.getData()) & eleicaoPrimeiroTurno.getIsSegundoTurnoNecessário() & dataAtual.isEqual(eleicaoSegundoTurno.getData())){
            System.out.println("ENTRANDO NO PRIMEIRO PASSO");
            votacaoSegundoTurno(eleicaoSegundoTurno);
        }else if(dataAtual.isEqual(eleicaoPrimeiroTurno.getData())){
            votacaoPrimeiroTurno(eleicaoPrimeiroTurno);
        }else if(dataAtual.isAfter(eleicaoPrimeiroTurno.getData()) & !eleicaoPrimeiroTurno.getIsSegundoTurnoNecessário()){
            return new ResponseEntity<>("A votação já foi encerrada você pode consultar os vencedora na secao de RESULTADO DAS ELEICOES", HttpStatus.BAD_REQUEST);
        }else if(dataAtual.isAfter(eleicaoPrimeiroTurno.getData()) & eleicaoPrimeiroTurno.getIsSegundoTurnoNecessário() & dataAtual.isBefore(eleicaoSegundoTurno.getData())){
            return new ResponseEntity<>("A votação ainda não esta habilitada, espere a que seja ingressada as informacoes atualizadas", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResultadoVotacaoDTO contagemVotosPorCategoria(String tipoEleicao) {

        Eleicao eleicao = eleicaoRepository.findByTipo(tipoEleicao);

        Long votosValidos = candidatosVotosRepository.countCandidatosVotosByVotoAndEleicao(VotoEnum.VALIDO, eleicao);
        Long votosBranco= candidatosVotosRepository.countCandidatosVotosByVotoAndEleicao(VotoEnum.BRANCO, eleicao);
        Long votosNulo= candidatosVotosRepository.countCandidatosVotosByVotoAndEleicao(VotoEnum.NULO, eleicao);

        Long votosTotais = votosValidos + votosNulo + votosBranco;

        eleicao.setVotosTotaisNulos(votosNulo);
        eleicao.setVotosTotaisBrancos(votosBranco);
        eleicao.setVotosTotaisValidos(votosValidos);
        eleicao.setVotosTotais(votosTotais);
        eleicaoRepository.save(eleicao);

        return new ResultadoVotacaoDTO(votosValidos, votosBranco, votosNulo);
    }

    public ResponseEntity<?>contagemVotosPorCandidato(String nomeCandidato, String tipoEleicao){

        Eleicao eleicao = eleicaoRepository.findByTipo(tipoEleicao);

        Candidatos candidato = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(nomeCandidato);
        Long votosValidosCandidatos = candidatosVotosRepository.countCandidatosVotosByCandidatoAndVotoAndEleicao(candidato, VotoEnum.VALIDO, eleicao);

        String textoResultado = "Votos totais validos para o candidato " +candidato.getNome()+ "no " + eleicao.getTipo() + ": "+votosValidosCandidatos;

        return new ResponseEntity<>(textoResultado, HttpStatus.OK);
    }

    public ResponseEntity<?>candidatoEleitoPorCargo(String nomeCargo, String eleicao){

        Eleicao eleicao1 = eleicaoRepository.findByTipo(eleicao);
        Cargos cargo = cargosRepository.findCargosByNomeContainsIgnoreCase(nomeCargo);
        String textoCandidatoEleito;

        if(cargo.getTurnosPossiveis() ==1){
            ResultadoPorCandidatosDTO candidatoEleito = candidatosVotosRepository.findCandidatoEleitoPorCargo(cargo.getId());

            if(eleicao1.getTipo().equalsIgnoreCase("Primeiro Turno")){
                textoCandidatoEleito ="O "+cargo.getNome()+ " eleito foi "+ candidatoEleito.getNome()
                        + " com "+ candidatoEleito.getTotalVotos()+" votos totais validos";
                Candidatos candidato = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(candidatoEleito.getNome());
                candidato.setEleito(true);
                candidatosRepository.save(candidato);
            } else{
                textoCandidatoEleito = "O cargo escolhido não possui segundo turno." + "O "+cargo.getNome()+ " eleito foi "+ candidatoEleito.getNome()
                        + " com "+ candidatoEleito.getTotalVotos()+" votos totais validos";
            }
            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(eleicaoPresidenteGovernador(cargo, eleicao1), HttpStatus.OK);
        }
    }


    //MÉTODOS GERAIS

    private void votacaoPrimeiroTurno(Eleicao eleicao){
        comecarVotacao(eleicao);
        new ResponseEntity<>(HttpStatus.OK);
    }

    private void votacaoSegundoTurno(Eleicao eleicao){
        System.out.println("ENTRANDO NO SEGUNDO PASSO");

        comecarVotacao(eleicao);
        new ResponseEntity<>(HttpStatus.OK);
    }

    private void comecarVotacao(Eleicao eleicao){

        Random random = new Random();

        Long numPresidente;
        int numRandom;
        String cargo = "Presidente";

        if(eleicao.getTipo().equalsIgnoreCase("Segundo Turno")){

            List<Candidatos> candidatosList = configuracaoSegundoTurno(eleicao);

            List<String> cargosVotacao = new ArrayList<>();
            List<Long> idPresidentes = new ArrayList<>();
            List<Long> idGovernadores = new ArrayList<>();

            for (Candidatos ca: candidatosList) {

                if(ca.getCargos().getNome().equalsIgnoreCase("Presidente")) {
                    if(!cargosVotacao.contains("Presidente")){cargosVotacao.add("Presidente"); }
                    idPresidentes.add(ca.getId());
                }
                if(ca.getCargos().getNome().equalsIgnoreCase("Governador")){
                    if(!cargosVotacao.contains("Governador")){cargosVotacao.add("Governador");}
                    idGovernadores.add(ca.getId());
                }
            }

            for (int i = 0; i < cargosVotacao.size(); i++) {
                switch (cargo){
                    case "Presidente":
                        for (int j = 0; j < eleicao.getVotosHabilitados() ; j++) {
                        numRandom = random.nextInt(2);
                        numPresidente = numRandom == 0 ? idPresidentes.get(0) : idPresidentes.get(1);
                            votar(eleicao, numPresidente);
                    }
                        break;
                    case "Governador":
                        for (int j = 0; j < eleicao.getVotosHabilitados() ; j++) {
                            numRandom = random.nextInt(2);
                            numPresidente = numRandom == 0 ? idGovernadores.get(0) : idGovernadores.get(1);
                            votar(eleicao, numPresidente);
                        }
                        break;
                }
                cargo = "Governador";
            }
        }
        else if(eleicao.getTipo().equalsIgnoreCase("Primeiro Turno")){
            while (!cargo.equals("Finalizado")){
                switch (cargo) {
                    case "Presidente" -> {
                        for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {
                            numRandom = random.nextInt(7) + 1;
                            numPresidente = (long) numRandom;
                            votar(eleicao, numPresidente);
                        }
                        cargo = "Governador";
                    }
                    case "Governador" -> {
                        for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {
                            numRandom = random.nextInt(10) + 8;
                            numPresidente = (long) numRandom;
                            votar(eleicao, numPresidente);
                        }
                        cargo = "Senador";
                    }
                    case "Senador" -> {
                        for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {
                            numRandom = random.nextInt(8) + 18;
                            numPresidente = (long) numRandom;
                            votar(eleicao, numPresidente);
                        }
                        cargo = "Deputado Federal";
                    }
                    case "Deputado Federal" -> {
                        for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {
                            numRandom = random.nextInt(5) + 26;
                            numPresidente = (long) numRandom;
                            votar(eleicao, numPresidente);
                        }
                        cargo = "Deputado Estadual";
                    }
                    case "Deputado Estadual" -> {
                        for (int i = 0; i < eleicao.getVotosHabilitados(); i++) {
                            numRandom = random.nextInt(9) + 31;
                            numPresidente = (long) numRandom;
                            votar(eleicao, numPresidente);
                        }
                        cargo = "Finalizado";
                    }
                }
            }
        }

    }

    private void votar(Eleicao eleicao, Long numPresidente){
        Random random = new Random();
        System.out.println("ENTRANDO NO VOTAR()");

            CandidatosVotos candidatosVotos = new CandidatosVotos();

            Optional<Candidatos> candidato = candidatosRepository.findById(numPresidente);

        System.out.println("O CANDIDATO SELECCIONADO É" + candidato.get().getNome() + " "+candidato.get().getId() + " " + candidato.get().getCargos());


        int opcVoto = random.nextInt(3)+1;

            votoEnum = (opcVoto == 1) ? VotoEnum.VALIDO : (opcVoto == 2) ? VotoEnum.BRANCO : VotoEnum.NULO;

            candidatosVotos.setCandidato(candidato.orElse(null));
            candidatosVotos.setEleicao(eleicao);
            candidatosVotos.setVoto(votoEnum);
            candidatosVotosRepository.save(candidatosVotos);

        System.out.println("VOLTANDO PARA O COMECAR VOTACAO");
    }

    private List<Candidatos> configuracaoSegundoTurno(Eleicao eleicao) {
        System.out.println("ENTRANDO NO QUINTO PASSO: CONFIGURAÇÃO");

        //Uma lista para conter todos os dados de Governador e Presidente, e depois revisar se há algum eleito
        List<Candidatos> candidatosList = new ArrayList<>(candidatosRepository.findAll()); //Coloco todos os candidatos em uma lista e depois com o Stream faço o filtro para trazer só governadores e presidentes

        List<Candidatos> candidatosSegundoTurno = candidatosList.stream()
                .filter(candidatos -> candidatos.getCargos().getNome().equals("Presidente") ||
                        candidatos.getCargos().getNome().equals("Governador"))
                .collect(Collectors.toList());


//        for (Candidatos ca:candidatosSegundoTurno) {
//            System.out.println("ENTRANDO NO FOR");
//            if(ca.getEleito() & ca.getCargos().getNome().equalsIgnoreCase("Presidente")){
//                presidenteEleito = true;
//            } else if(ca.getEleito() & ca.getCargos().getNome().equalsIgnoreCase("Governador")){
//                governadorEleito = true;
//            }
//        }

        //Forma simplificada usando Stream para verificar se existe algum presidente ou Governador Eleito. Com o .anyMatch() determino que quero ver se existe algum candidato com o booleano eleito == true
        //Simplifica o for feito anteriormente
        boolean presidenteEleito = candidatosSegundoTurno.stream()
                .anyMatch(ca -> ca.getEleito() && ca.getCargos().getNome().equalsIgnoreCase("Presidente"));

        boolean governadorEleito = candidatosSegundoTurno.stream()
                .anyMatch(ca -> ca.getEleito() && ca.getCargos().getNome().equalsIgnoreCase("Governador"));
        candidatosList.clear();

        Cargos cargosP = cargosRepository.findCargosByNomeContainsIgnoreCase("Presidente");
        Cargos cargosG = cargosRepository.findCargosByNomeContainsIgnoreCase("Governador");

        if (!presidenteEleito) {
            System.out.println("ENTRANDO NO IF DO PRESIDENTE ELEITO = FALSE");
            List<ResultadoPorCandidatosDTO> ganhadoresPrimeiroTurnoPres = candidatosVotosRepository.findCandidatosVotosByCargoAndVotoAndEleicao(cargosP.getId(), 1L);

            System.out.println("candidatosSegundoTurnoP.size(): " + ganhadoresPrimeiroTurnoPres.size());

            ganhadoresPrimeiroTurnoPres.stream()
                    .limit(2)
                    .map(resultado -> candidatosRepository.findCandidatosByNomeContainsIgnoreCase(resultado.getNome()))
                    .forEach(candidatosList::add);

            for (Candidatos cand: candidatosList) {
                System.out.println("Presidentes segundo turno: " + cand.getNome() + " " + cand.getCargos());
            }
        }

        if (!governadorEleito) {
            System.out.println("ENTRANDO NO IF DO GOVERNADOR ELEITO = FALSE");
            List<ResultadoPorCandidatosDTO> ganhadoresPrimeiroTurnoGov = candidatosVotosRepository.findCandidatosVotosByCargoAndVotoAndEleicao(cargosG.getId(), 1L);

            ganhadoresPrimeiroTurnoGov.stream()
                    .limit(2)
                    .map(resultado -> candidatosRepository.findCandidatosByNomeContainsIgnoreCase(resultado.getNome()))
                    .forEach(candidatosList::add);

            for (Candidatos cand: candidatosList) {
                System.out.println("Governadores segundo turno: " + cand.getNome() + " " + cand.getCargos());
            }
        }

//
//        if(!presidenteEleito){
//            System.out.println("ENTRANDO NO IF DO PRESIDENTE ELEITO = FALSE");
//            List<ResultadoPorCandidatosDTO> candidatosSegundoTurnoP = candidatosVotosRepository.findCandidatosVotosByCargoAndVotoAndEleicao(cargoPresidente.getId(), eleicao.getId());//Pego a lista com os candidatos com seus votos ordenados do maior ao menor
//
//            System.out.println("candidatosSegundoTurnoP.size()"+candidatosSegundoTurnoP.size());
//            for (int i = 0; i < 2; i++) {
//                System.out.println("entrando no segundo foooooor");
//                Candidatos candidatos = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(candidatosSegundoTurnoP.get(i).getNome());
//                candidatosList.add(candidatos);
//            }
//
//            // candidatosList.addAll(presidentesList);
//
//            for (Candidatos cand: candidatosList) {
//                System.out.println("Presidentes segundo turno: " + cand.getNome() + " "+ cand.getCargos());
//            }
//        }
//        if (!governadorEleito){
//            System.out.println("ENTRANDO NO IF DO GOVERNADOR ELEITO = FALSE");
//
//            List<ResultadoPorCandidatosDTO> candidatosSegundoTurnoP = candidatosVotosRepository.findCandidatosVotosByCargoAndVotoAndEleicao(cargoGovernador.getId(), eleicao.getId());
//            //  governadoresList.clear();
//
//            for (int i = 0; i < 2; i++) {
//                Candidatos candidatos = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(candidatosSegundoTurnoP.get(i).getNome());
//                candidatosList.add(candidatos);
//            }
//
//            // candidatosList.addAll(governadoresList);
//
//            for (Candidatos cand: candidatosList) {
//                System.out.println("Presidentes segundo turno: " + cand.getNome() + " "+ cand.getCargos());
//            }
//        }

        return candidatosList;
    }

    private ResponseEntity<?> eleicaoPresidenteGovernador(Cargos cargos, Eleicao eleicao) throws NumberFormatException{

        System.out.println(cargos.getId() +"ID CARGO");
        System.out.println(eleicao.getId() + "ID ELEICAO");
        ResultadoPorCandidatosDTO candidatoEleito = candidatosVotosRepository.findCandidatoEleitoPorCargoAndEleicao(cargos.getId(), eleicao.getId());   //Retorna o candidato com maior numero de votos

        Long totalVotos = candidatosVotosRepository.countVotosByCargoAndVotoValidoAndEleicao(cargos.getId(), eleicao.getId() );         //Retorna o total de votos validos para todos os candidatos para o cargo de presidente ou governador

        Double porcentagemEleitoral = 0.51 * totalVotos;        //É definido a quantidade de votos que o candidato deve ter para poder ser eleito, dependendo de quantos votos validos totais houveram entre todos os candidatos

        Long totalVotoCandidato =  candidatoEleito.getTotalVotos(); //Define quantos votos o candidato obteve

        String textoCandidatoEleito = "O candidato eleito a "+ cargos.getNome() + " foi " + candidatoEleito.getNome() +
                " com " + candidatoEleito.getTotalVotos() +" votos totais validos";

        if( totalVotoCandidato > porcentagemEleitoral){
            Candidatos candidato = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(candidatoEleito.getNome());
            candidato.setEleito(true);
            candidatosRepository.save(candidato);
            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
        else if( totalVotoCandidato < porcentagemEleitoral & eleicao.getTipo().equalsIgnoreCase("Primeiro turno") ){
            eleicao.setIsSegundoTurnoNecessário(true);
            eleicaoRepository.save(eleicao);
            configuracaoSegundoTurno(eleicao);
            return new ResponseEntity<>("Será necessário um segundo turno, nenhum candidato " +
                    "alcançou a porcentagem necessária", HttpStatus.OK);
        }else if(totalVotoCandidato < porcentagemEleitoral & eleicao.getTipo().equalsIgnoreCase("Segundo turno")){
            eleicao.setIsSegundoTurnoNecessário(false);
            eleicaoRepository.save(eleicao);
            Candidatos candidato = candidatosRepository.findCandidatosByNomeContainsIgnoreCase(candidatoEleito.getNome());
            candidato.setEleito(true);
            candidatosRepository.save(candidato);
            return new ResponseEntity<>(textoCandidatoEleito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
