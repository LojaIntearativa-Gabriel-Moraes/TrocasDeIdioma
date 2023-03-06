package com.example.TrocaDeIdioma.model;

import lombok.Data;
import org.hibernate.engine.jdbc.dialect.spi.DialectFactory;

import javax.persistence.*;
import java.text.Normalizer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

@Entity
@Data
public class Disponibilidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "professor_id")
  private Professor user;

  private LocalTime horaInicio;

  private LocalTime horaFim;

  @ElementCollection(targetClass = DiaSemana.class)
  @Enumerated(EnumType.STRING)
  private Set<DiaSemana> diasDisponiveis;


  public boolean isAvailable(LocalTime startTime, LocalTime endTime, DiaSemana dia) {
    if (!diasDisponiveis.contains(dia)) {
      return false; // O professor não está disponível nesse dia
    }

    if (startTime.isBefore(horaInicio) || endTime.isAfter(horaFim)) {
      return false; // O intervalo de tempo está fora dos limites de disponibilidade do professor
    }

    return true; // O professor está disponível no dia e horário especificados
  }

  public boolean isAvailable(LocalDateTime startTime) {
    DayOfWeek dayOfWeek = startTime.getDayOfWeek();

    DiaSemana diaSemana = formatarLocalDateTimeParaEnum(startTime);
    if (!diasDisponiveis.contains(diaSemana)) {
      return false; // O professor não está disponível nesse dia
    }

    if (startTime.toLocalTime().isBefore(horaInicio) || startTime.toLocalTime().isAfter(horaFim)) {
      return false; // O intervalo de tempo está fora dos limites de disponibilidade do professor
    }

    return true; // O professor está disponível no dia e horário especificados
  }
  private DiaSemana formatarLocalDateTimeParaEnum(LocalDateTime dia) {
    DayOfWeek dayOfWeek = dia.getDayOfWeek();
    String diaSemanaFormatadoSemTraco = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).toLowerCase().replace("-", "_");
    String nomeDiaSemanaFormatado = diaSemanaFormatadoSemTraco.substring(0, 1).toUpperCase() + diaSemanaFormatadoSemTraco.substring(1);
    String nomeDiaSemanaFormatadoSemAcento = removerAcentos(nomeDiaSemanaFormatado);
    return DiaSemana.valueOf(nomeDiaSemanaFormatadoSemAcento);
  }

  private String removerAcentos(String texto) {
    if (texto != null) {
      texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
      texto = texto.replaceAll("[^\\p{ASCII}]", "");
    }
    return texto;
  }

}

