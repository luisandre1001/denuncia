package com.andre.denuncia.repository;

import com.andre.denuncia.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    List<Denuncia> findByTipoAgente(String tipoAgente);
    List<Denuncia> findByStatus(String status);
}
