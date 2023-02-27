package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aluno;
import com.example.TrocaDeIdioma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {


}
