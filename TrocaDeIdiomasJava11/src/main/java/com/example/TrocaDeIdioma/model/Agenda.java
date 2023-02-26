package com.example.TrocaDeIdioma.model;

import lombok.Data;

import java.time.LocalDateTime;
import javax.persistence.*;
@Entity
@Data
public class Agenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "partner_id")
  private User partner;

  @Column(name = "start_time")
  private LocalDateTime startTime;

  @Column(name = "end_time")
  private LocalDateTime endTime;


  @ManyToOne
  @JoinColumn(name = "learning_language_id")
  private Idioma learningLanguage;

  // getters e setters
}
