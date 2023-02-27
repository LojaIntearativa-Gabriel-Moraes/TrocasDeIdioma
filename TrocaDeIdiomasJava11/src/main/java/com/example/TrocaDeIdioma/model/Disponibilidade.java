package com.example.TrocaDeIdioma.model;

import lombok.Data;
import org.hibernate.engine.jdbc.dialect.spi.DialectFactory;

import javax.persistence.*;
import java.time.LocalDate;
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

  @OneToOne(fetch = FetchType.LAZY)
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
}
