package com.example.TrocaDeIdioma.model;

public enum DiaSemana {
  Segunda_feira("Segunda-feira"),
  Terca_feira("Terça-feira"),
  Quarta_feira("Quarta-feira"),
  Quinta_feira("Quinta-feira"),
  Sexta_feira("Sexta-feira"),
  Sabado("Sábado"),
  Domingo("Domingo");

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
