package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.*;
import com.example.TrocaDeIdioma.model.Request.SolicitacaoAulaRequest;
import com.example.TrocaDeIdioma.repository.AulaRepository;
import com.example.TrocaDeIdioma.repository.SolicitacaoAulaRepository;
import com.example.TrocaDeIdioma.repository.UserRepository;
import com.example.TrocaDeIdioma.service.security.BuscarUsuarioSecuritySerivce;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@Service
public class SolicitacaoAulaService {

  @Autowired
  private SolicitacaoAulaRepository solicitacaoAulaRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private ProfessorService professorService;

  @Autowired
  private AulaRepository aulaRepository;

  @Autowired
  private UsuarioAutenticadoService buscarUsuarioAutenticadoService;

  @Transactional
  public void solicitarAula(SolicitacaoAulaRequest request) {

    Aluno aluno = alunoService.porId(request.getIdAluno());
    Professor professor = professorService.porId(request.getIdProfessor());

    SolicitacaoAula solicitacao = new SolicitacaoAula();
    solicitacao.setAluno(aluno);
    solicitacao.setProfessor(professor);

    solicitacao.setDataHoraInicio(request.getDataHoraInicio());
    solicitacao.setDataHoraFim(request.getDataHoraFim());
    solicitacao.setIdioma(request.getIdioma());

    solicitacao.setNivel(aluno.getNivel());
    solicitacao.setStatus(StatusSolicitacaoAula.PENDENTE);

    solicitacaoAulaRepository.save(solicitacao);

    aluno.adicionarSolicitacaoAula(solicitacao);
    professor.adicionarSolicitacaoAula(solicitacao);

    aluno.debitarSaldo(solicitacao.getValorHora());

    alunoService.salvar(aluno);
    professorService.salvar(professor);
  }

  @Transactional
  public void aceitarSolicitacao(Long id) {

    User usuarioLogado = buscarUsuarioAutenticadoService.get();



    SolicitacaoAula solicitacao = porId(id);

    if(solicitacao.getProfessor().getId() != usuarioLogado.getId()) {
      throw new ResponseStatusException(BAD_REQUEST,"Voce nao tem permissão para isso");
    }

    if (solicitacao.getStatus() != StatusSolicitacaoAula.PENDENTE) {
      throw new ResponseStatusException(NOT_FOUND,"Não é possível confirmar uma aula que não está pendente");
    }

    solicitacao.setStatus(StatusSolicitacaoAula.ACEITO);

    Aula aula = Aula.builder()
      .aluno(solicitacao.getAluno())
      .professor(solicitacao.getProfessor())
      .dataHoraInicio(solicitacao.getDataHoraInicio())
      .dataHoraFim(solicitacao.getDataHoraFim())
      .idioma(solicitacao.getIdioma())
      .nivel(solicitacao.getNivel())
      .valorDaAula(solicitacao.getProfessor().getValorPorHora())
      .build();

    Professor professor = solicitacao.getProfessor();
    professor.adicionarAula(aula);
    professor.removerSolicitacaoAula(solicitacao);

    Aluno aluno = solicitacao.getAluno();
    aluno.adicionarAula(aula);
    aluno.removerSolicitacaoAula(solicitacao);


    aulaRepository.save(aula);
    solicitacaoAulaRepository.save(solicitacao);
    professorService.salvar(solicitacao.getProfessor());
    alunoService.salvar(solicitacao.getAluno());
  }

  @Transactional
  public void recusarSolicitacao(Long id) {
    User usuarioLogado = buscarUsuarioAutenticadoService.get();
    SolicitacaoAula solicitacao = porId(id);

    if(solicitacao.getProfessor().getId() != usuarioLogado.getId()) {
      throw new ResponseStatusException(BAD_REQUEST,"Voce nao tem permissão para isso");
    }

    if (solicitacao.getStatus() != StatusSolicitacaoAula.PENDENTE) {
      throw new ResponseStatusException(NOT_FOUND,"Não é possível recusar uma aula que não está pendente");
    }

    solicitacao.setStatus(StatusSolicitacaoAula.RECUSADO);

    solicitacao.getAluno().removerSolicitacaoAula(solicitacao);

    solicitacaoAulaRepository.save(solicitacao);
    alunoService.salvar(solicitacao.getAluno());
  }


  public SolicitacaoAula porId(Long id) {
    return solicitacaoAulaRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "SolicitacaoAula não encontrado"));
  }
}
