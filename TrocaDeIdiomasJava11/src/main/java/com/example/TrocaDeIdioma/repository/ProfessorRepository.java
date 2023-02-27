package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

  List<Professor> findByIdiomas(String language);


}
