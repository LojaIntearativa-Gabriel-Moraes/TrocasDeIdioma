package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.mapper.ProfessorMapper;
import com.example.TrocaDeIdioma.model.DiaSemana;
import com.example.TrocaDeIdioma.model.Disponibilidade;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import com.example.TrocaDeIdioma.model.User;
import com.example.TrocaDeIdioma.repository.AlunoRepository;
import com.example.TrocaDeIdioma.repository.ProfessorRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository repository;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;


  public Professor porId(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Professor não encontrado"));
  }

  public Professor professorAutenticado() {
    return repository.findById(usuarioAutenticadoService.getId())
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Professor não encontrado"));
  }
  public void salvar(Professor professor) {
    repository.save(professor);
  }

  public List<ProfessorResponse> findAvailableProfsByLanguage(String language, LocalTime startTime, LocalTime endTime, String dia) {

    DiaSemana verificarDia = DiaSemana.valueOf(dia);
    List<Professor> availableProfs = new ArrayList<>();

    List<Professor> professors = repository.findByIdiomas(language);

    for (Professor prof : professors) {
      Disponibilidade disponibilidade = prof.getDisponibilidade();

      if (disponibilidade != null && disponibilidade.isAvailable(startTime, endTime, verificarDia)) {
        availableProfs.add(prof);
      }
    }

    return ProfessorMapper.toResponse(availableProfs);
  }
}
