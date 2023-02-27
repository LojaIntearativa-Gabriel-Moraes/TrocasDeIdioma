package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aulas")
public class AulaController {

  @Autowired
  private AulaService aulaService;

  @PostMapping("/{id}/concluir")
  public ResponseEntity<?> concluirAula(@PathVariable Long id) {
    aulaService.concluirAula(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}/aluno/cancelar")
  public ResponseEntity<?> alunoCancelarAula(@PathVariable Long id) {
    aulaService.alunoCancelarAula(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}/professor/cancelar")
  public ResponseEntity<?> professorCancelarAula(@PathVariable Long id) {
    aulaService.professorCancelarAula(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
