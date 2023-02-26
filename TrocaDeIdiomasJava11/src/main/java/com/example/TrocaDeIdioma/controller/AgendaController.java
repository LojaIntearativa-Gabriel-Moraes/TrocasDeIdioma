package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Agenda;
import com.example.TrocaDeIdioma.model.Request.AgendaRequest;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

  @Autowired
  private AgendaService agendaService;

  @GetMapping
  public List<Agenda> getAgendas() {
    return agendaService.getAgendas();
  }

  @PostMapping
  public Agenda createAgenda(@RequestBody AgendaRequest agendaRequest) {
    User user = agendaRequest.getUser();
    User partner = agendaRequest.getPartner();
    LocalDateTime startTime = agendaRequest.getStartTime();
    LocalDateTime endTime = agendaRequest.getEndTime();
    return agendaService.createAgenda(user, partner, startTime, endTime);
  }

  @PutMapping("/{id}")
  public Agenda updateAgenda(@PathVariable Long id, @RequestBody AgendaRequest agendaRequest) {
    User user = agendaRequest.getUser();
    User partner = agendaRequest.getPartner();
    LocalDateTime startTime = agendaRequest.getStartTime();
    LocalDateTime endTime = agendaRequest.getEndTime();
    return agendaService.updateAgenda(id, user, partner, startTime, endTime);
  }

  @DeleteMapping("/{id}")
  public void deleteAgenda(@PathVariable Long id) {
    agendaService.deleteAgenda(id);
  }
}
