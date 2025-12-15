package com.andre.denuncia.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RelatorioOperacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Denuncia denuncia;
    private String relatorio;
    private String fotoPath;
    private LocalDateTime dataHora;
}
