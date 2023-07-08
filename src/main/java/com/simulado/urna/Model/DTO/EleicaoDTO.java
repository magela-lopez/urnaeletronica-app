package com.simulado.urna.Model.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter@Setter
public class EleicaoDTO {

    private String tipo;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate data;

    private Long votosHabilitados;
}
