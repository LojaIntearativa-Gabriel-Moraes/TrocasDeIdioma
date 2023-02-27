package com.example.TrocaDeIdioma.service;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.repository.AlunoRepository;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository repository;
  @Autowired
  private UsuarioAutenticadoService usuarioAutenticadoService;


  public Aluno porId(Long id) {
    return repository.findById(usuarioAutenticadoService.getId())
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Aluno não encontrado"));
  }

  public void salvar(Aluno aluno) {
    repository.save(aluno);
  }
}
