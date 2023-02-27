package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Request.SolicitacaoAulaRequest;
import com.example.TrocaDeIdioma.service.SolicitacaoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoAulaController {

  @Autowired
  private SolicitacaoAulaService solicitacaoAulaService;

  @PostMapping("/aula")
  public ResponseEntity<?> solicitarAula(@RequestBody SolicitacaoAulaRequest request) {
    solicitacaoAulaService.solicitarAula(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{id}/aceitar")
  public ResponseEntity<?> aceitarSolicitacao(@PathVariable Long id) {
    solicitacaoAulaService.aceitarSolicitacao(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/recusar")
  public ResponseEntity<?> recusarSolicitacao(@PathVariable Long id) {
    solicitacaoAulaService.recusarSolicitacao(id);
    return ResponseEntity.noContent().build();
  }


}
