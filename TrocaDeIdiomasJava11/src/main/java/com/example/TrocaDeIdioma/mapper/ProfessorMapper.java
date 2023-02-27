package com.example.TrocaDeIdioma.mapper;

import com.example.TrocaDeIdioma.model.Professor;
import com.example.TrocaDeIdioma.model.Response.ProfessorResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorMapper {
    public static ProfessorResponse toResponse(Professor entity) {
        return new ModelMapper().map(entity, ProfessorResponse.class);
    }

    public static List<ProfessorResponse> toResponse(List<Professor> list) {
        return list.stream()
                .map(entity -> new ModelMapper().map(entity, ProfessorResponse.class))
                .collect(Collectors.toList());
    }
}

