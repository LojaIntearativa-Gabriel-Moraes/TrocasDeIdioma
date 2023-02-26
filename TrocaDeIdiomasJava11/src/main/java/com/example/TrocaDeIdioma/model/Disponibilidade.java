package com.example.TrocaDeIdioma.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Disponibilidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDate data;

  private LocalTime horaInicio;

  private LocalTime horaFim;

  // construtores, getters e setters
}
