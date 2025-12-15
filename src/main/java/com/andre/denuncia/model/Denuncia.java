package com.andre.denuncia.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoAgente; // POLICIA, BOMBEIROS, MEDICO
    private String descricao;
    private String audioPath;
    private double latitude;
    private double longitude;
    private LocalDateTime dataHora;
    private String status; // NOVA, PENDENTE, FINALIZADA
    @ManyToOne
    private Agente agente;
}
