package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.repository.DisponibilidadeRepository;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadeService {

  @Autowired
  private ProfessorService professorService;
  private final DisponibilidadeRepository disponibilidadeRepository;


  public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository) {
    this.disponibilidadeRepository = disponibilidadeRepository;
  }

  public Disponibilidade save(Disponibilidade disponibilidade) {
    Professor professor = professorService.professorAutenticado();

    disponibilidade.setUser(professor);

    return disponibilidadeRepository.save(disponibilidade);
  }

  public List<Disponibilidade> findAll() {
    return disponibilidadeRepository.findAll();
  }

  public Disponibilidade findById(Long id) {
    Optional<Disponibilidade> optional = disponibilidadeRepository.findById(id);
    return optional.get();
  }

  public void deleteById(Long id) {
    disponibilidadeRepository.deleteById(id);
  }


  public Disponibilidade update(Long id, Disponibilidade disponibilidade) {
    Disponibilidade disponibilidadeUpdate = findById(id);
    disponibilidadeUpdate.setHoraInicio(disponibilidade.getHoraInicio());
    disponibilidadeUpdate.setHoraFim(disponibilidade.getHoraFim());
    disponibilidade.setDiasDisponiveis(disponibilidade.getDiasDisponiveis());
    disponibilidadeUpdate.setUser(disponibilidade.getUser());
    return disponibilidadeRepository.save(disponibilidadeUpdate);
  }
}
