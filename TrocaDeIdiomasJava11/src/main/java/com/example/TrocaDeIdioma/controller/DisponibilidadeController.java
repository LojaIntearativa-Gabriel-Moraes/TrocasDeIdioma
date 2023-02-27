package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
public class DisponibilidadeController {

  @Autowired
  private DisponibilidadeService disponibilidadeService;

  @PostMapping
  public ResponseEntity<Disponibilidade> save(@RequestBody Disponibilidade disponibilidade) {
    Disponibilidade result = disponibilidadeService.save(disponibilidade);
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<List<Disponibilidade>> findAll() {
    List<Disponibilidade> disponibilidades = disponibilidadeService.findAll();
    return ResponseEntity.ok(disponibilidades);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Disponibilidade> findById(@PathVariable Long id) {
    Disponibilidade disponibilidade = disponibilidadeService.findById(id);
    return ResponseEntity.ok(disponibilidade);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Disponibilidade> update(@PathVariable Long id, @RequestBody Disponibilidade disponibilidade) {
    Disponibilidade result = disponibilidadeService.update(id, disponibilidade);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    disponibilidadeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
