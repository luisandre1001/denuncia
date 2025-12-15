package com.andre.denuncia.service;

import com.andre.denuncia.model.Agente;
import com.andre.denuncia.repository.AgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class AgenteService {
    @Autowired
    private AgenteRepository agenteRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Agente cadastrar(Agente agente) {
        agente.setSenha(passwordEncoder.encode(agente.getSenha()));
        return agenteRepository.save(agente);
    }

    public Agente autenticar(String login, String senha) {
        Agente agente = agenteRepository.findByLogin(login);
        if (agente != null && passwordEncoder.matches(senha, agente.getSenha())) {
            return agente;
        }
        return null;
    }
}
