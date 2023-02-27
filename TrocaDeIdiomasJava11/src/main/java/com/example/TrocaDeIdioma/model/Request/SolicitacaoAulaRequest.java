package com.example.TrocaDeIdioma.model.Request;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class SolicitacaoAulaRequest {

  private Long idAluno;
  private Long idProfessor;
  private LocalDateTime dataHoraInicio;

  private LocalDateTime dataHoraFim;
  private String idioma;
  private String status;
}
