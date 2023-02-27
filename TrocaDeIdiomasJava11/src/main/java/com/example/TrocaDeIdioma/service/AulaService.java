package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.repository.AulaRepository;
import com.example.TrocaDeIdioma.repository.UserRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AulaService {

  @Autowired
  private AulaRepository repository;

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;

  @Autowired
  private UserRepository userRepository;
  @Transactional
  public void alunoCancelarAula(Long aulaId) {

    Aula aula = porId(aulaId);
    User usuarioAutenticado = usuarioAutenticadoService.get();
    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(BAD_REQUEST, "Não é possível cancelar uma aula que não está confirmada");
    }

    if(aula.getAluno().getId() != usuarioAutenticado.getId()){
      throw new ResponseStatusException(BAD_REQUEST, "Voce não tem permissao para fazer isso");
    }

    aula.setStatus(StatusAula.CANCELADA_ALUNO);

    Professor professor = professorService.porId(aula.getProfessor().getId());
    Aluno aluno = alunoService.porId(aula.getAluno().getId());

    professor.adicionarSaldo(aula.getValorDaAula().divide(new BigDecimal("2")).setScale(2));
    aluno.adicionarSaldo(aula.getValorDaAula().divide(new BigDecimal("2")).setScale(2));
    save(aula);

  }

  @Transactional
  public void professorCancelarAula(Long aulaId) {

    Aula aula = porId(aulaId);
    User usuarioAutenticado = usuarioAutenticadoService.get();

    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(BAD_REQUEST, "Não é possível cancelar uma aula que não está confirmada");
    }
    if(aula.getProfessor().getId() != usuarioAutenticado.getId()){
      throw new ResponseStatusException(BAD_REQUEST, "Voce não tem permissao para fazer isso");
    }

    aula.setStatus(StatusAula.CANCELADA_PROFESSOR);
    aula.setCancelada(true);
    aula.setDataHoraCancelamento(LocalDateTime.now());

    Aluno aluno = alunoService.porId(aula.getAluno().getId());
    aluno.adicionarSaldo(aula.getValorDaAula());


    save(aula);
  }

  @Transactional
  public void concluirAula(Long aulaId) {

    Aula aula = porId(aulaId);

    if (aula.getStatus() != StatusAula.AGENDADA) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível concluir uma aula que não está confirmada");
    }

    aula.setStatus(StatusAula.CONCLUIDA);
    aula.setDataHoraFim(LocalDateTime.now());

    User professor = aula.getProfessor();
    BigDecimal valorDaAula = aula.getValorDaAula();

    professor.adicionarSaldo(valorDaAula);

    userRepository.save(professor);
    repository.save(aula);
  }
  public Aula save(Aula aula) {
    return repository.save(aula);
  }
  public Aula porId(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Aula não encontrado"));
  }


}
