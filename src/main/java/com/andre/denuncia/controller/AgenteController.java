package com.andre.denuncia.controller;

import com.andre.denuncia.model.Agente;
import com.andre.denuncia.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agentes")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;

    @PostMapping("/cadastro")
    public Agente cadastrar(@RequestBody Agente agente) {
        return agenteService.cadastrar(agente);
    }

    @PostMapping("/login")
    public Agente login(@RequestParam String login, @RequestParam String senha) {
        return agenteService.autenticar(login, senha);
    }
}
