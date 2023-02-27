package com.example.TrocaDeIdioma.model;

public enum DiaSemana {
  SEGUNDA_FEIRA("Segunda-feira"),
  TERCA_FEIRA("Terça-feira"),
  QUARTA_FEIRA("Quarta-feira"),
  QUINTA_FEIRA("Quinta-feira"),
  SEXTA_FEIRA("Sexta-feira"),
  SABADO("Sábado"),
  DOMINGO("Domingo");

  private String descricao;

  private DiaSemana(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public static DiaSemana fromValue(String value) {
    for (DiaSemana dia : DiaSemana.values()) {
      if (dia.descricao.equalsIgnoreCase(value)) {
        return dia;
      }
    }
    throw new IllegalArgumentException("Valor inválido para DiaSemana: " + value);
  }
}
