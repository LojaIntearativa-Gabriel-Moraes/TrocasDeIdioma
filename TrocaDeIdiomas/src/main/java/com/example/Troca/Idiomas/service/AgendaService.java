package com.example.Troca.Idiomas.service;

import com.example.Troca.Idiomas.model.Agenda;
import com.example.Troca.Idiomas.model.User;
import com.example.Troca.Idiomas.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

  @Autowired
  private AgendaRepository agendaRepository;

  public List<Agenda> getAgendas() {
    return agendaRepository.findAll();
  }
  public Agenda createAgenda(User user, User partner, LocalDateTime startTime, LocalDateTime endTime) {
    Agenda agenda = new Agenda();
    agenda.setUser(user);
    agenda.setPartner(partner);
    agenda.setStartTime(startTime);
    agenda.setEndTime(endTime);
    return agendaRepository.save(agenda);
  }

  public Agenda updateAgenda(Long id, User user, User partner, LocalDateTime startTime, LocalDateTime endTime) {
    Optional<Agenda> optionalAgenda = agendaRepository.findById(id);
    if (optionalAgenda.isPresent()) {
      Agenda agenda = optionalAgenda.get();
      agenda.setUser(user);
      agenda.setPartner(partner);
      agenda.setStartTime(startTime);
      agenda.setEndTime(endTime);
      return agendaRepository.save(agenda);
    } else {
      throw new RuntimeException("Agenda not found");
    }
  }

  public void deleteAgenda(Long id) {
    agendaRepository.deleteById(id);
  }
}
