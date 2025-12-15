package com.andre.denuncia.repository;

import com.andre.denuncia.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, Long> {
    Agente findByLogin(String login);
}
