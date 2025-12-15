package com.andre.denuncia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo; // POLICIA, BOMBEIROS, MEDICO
    private String login;
    private String senha;
    private String email;
}
