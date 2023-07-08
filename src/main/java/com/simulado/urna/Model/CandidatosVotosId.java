/*Não é possível utilizar esta classe pois na votação teremos muitos votos para o mesmo candidato na mesma eleicao
isso fará com que os id's se repitam e fará com que a chave composta (o id) da tabela CandidatosVotos seja duplicada e '
não pode ser, a chave deve ser única.*/


//package com.simulado.urna.Model;
//
//import lombok.*;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import java.io.Serializable;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Embeddable
//public class CandidatosVotosId implements Serializable {
//
//    @EqualsAndHashCode.Include
//    @Column(name = "CandidatosId")
//    private Long candidatosId;
//
//    @EqualsAndHashCode.Include
//    @Column(name = "EleicaoId")
//    private Long eleicaoId;
//
//    // Construtores, getters e setters
//
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
////        CandidatosVotosId that = (CandidatosVotosId) o;
////        return Objects.equals(candidatosId, that.candidatosId) &&
////                Objects.equals(eleicaoId, that.eleicaoId);
////    }
////
////    @Override
////    public int hashCode() {
////        return Objects.hash(candidatosId, eleicaoId);
////    }
//}
