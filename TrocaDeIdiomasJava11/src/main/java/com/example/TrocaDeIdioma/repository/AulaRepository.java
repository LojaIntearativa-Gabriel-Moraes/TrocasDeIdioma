package com.example.TrocaDeIdioma.repository;

import com.example.TrocaDeIdioma.model.Aula;
import com.example.TrocaDeIdioma.model.StatusAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    List<Aula> findAllByProfessorId(Long professorId);

  List<Aula> findAllByProfessorIdAndStatus(Long idProfessor, StatusAula status);

  List<Aula> findAllByAlunoIdAndStatus(Long id, StatusAula agendada);
}
