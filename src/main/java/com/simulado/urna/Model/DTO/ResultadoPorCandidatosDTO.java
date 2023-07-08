package com.simulado.urna.Model.DTO;

public interface ResultadoPorCandidatosDTO {

    //Deve ser uma interface para que o String JPA possa identificar os atributos com as colunas que preciso recuperar da consulta
    //en la consulta nativa, los nombres de las columna del resultado de la consulta deben coincidir con los nombres de los métodos en la interfaz (getNome() y getTotalVotos() para que la proyección funcione correctamente.
    String getNome();
    Long getTotalVotos();

}
