package com.example.TrocaDeIdioma.controller;

import com.example.TrocaDeIdioma.model.Response.LoginResponse;
import com.example.TrocaDeIdioma.service.security.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public LoginResponse login() {
        LoginResponse response = new LoginResponse();
        response.setUsuarioId(usuarioAutenticadoService.getId());
        return response;
    }
}
