package com.example.TrocaDeIdioma.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Builder
@Data
public class Aula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "professor_id")
  private Professor professor;

  @ManyToOne
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @Column
  private String idioma;

  @Column(name = "data_hora_inicio")
  private LocalDateTime dataHoraInicio;

  @Column(name = "data_hora_fim")
  private LocalDateTime dataHoraFim;

  @Column(name = "valor_hora")
  private BigDecimal valorDaAula;

  @Column()
  private Long nivel;

  @Column(name = "status_aula")
  @Enumerated(EnumType.STRING)
  private StatusAula status;
  @Column(name = "data_hora_cancelamento")
  private LocalDateTime dataHoraCancelamento;

  @Column(name = "cancelada")
  private Boolean cancelada;


  // getters e setters
}


