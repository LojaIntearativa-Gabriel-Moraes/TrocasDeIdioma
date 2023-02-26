package com.example.TrocaDeIdioma.model.Response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IncluirUsuarioRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

}
