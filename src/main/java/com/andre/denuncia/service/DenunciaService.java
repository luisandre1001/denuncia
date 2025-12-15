package com.andre.denuncia.service;

import com.andre.denuncia.model.Agente;
import com.andre.denuncia.model.Denuncia;
import com.andre.denuncia.model.RelatorioOperacao;
import com.andre.denuncia.repository.AgenteRepository;
import com.andre.denuncia.repository.DenunciaRepository;
import com.andre.denuncia.repository.RelatorioOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private AgenteRepository agenteRepository;
    @Autowired
    private RelatorioOperacaoRepository relatorioOperacaoRepository;

    // Cidadão cria denúncia
    public Denuncia salvar(Denuncia denuncia) {
        denuncia.setStatus("NOVA");
        denuncia.setDataHora(LocalDateTime.now());
        return denunciaRepository.save(denuncia);
    }

    // Listar denúncias por tipo de agente
    public List<Denuncia> listarPorTipo(String tipo) {
        return denunciaRepository.findByTipoAgente(tipo.toUpperCase());
    }

    // Agente "recebe"
    public Denuncia marcarRecebido(Long denunciaId, Long agenteId) {
        Optional<Denuncia> denunciaOpt = denunciaRepository.findById(denunciaId);
        Optional<Agente> agenteOpt = agenteRepository.findById(agenteId);

        if (denunciaOpt.isPresent() && agenteOpt.isPresent()) {
            Denuncia denuncia = denunciaOpt.get();
            denuncia.setStatus("PENDENTE");
            denuncia.setAgente(agenteOpt.get());
            return denunciaRepository.save(denuncia);
        }
        return null;
    }

    // Agente finaliza operação (relatório/foto)
    public Denuncia finalizar(Long denunciaId, RelatorioOperacao relatorio) {
        Optional<Denuncia> denunciaOpt = denunciaRepository.findById(denunciaId);
        if (denunciaOpt.isPresent()) {
            Denuncia denuncia = denunciaOpt.get();
            denuncia.setStatus("FINALIZADA");

            // Salva relatório da operação
            relatorio.setDenuncia(denuncia);
            relatorio.setDataHora(LocalDateTime.now());
            relatorioOperacaoRepository.save(relatorio);

            return denunciaRepository.save(denuncia);
        }
        return null;
    }

    // Listar todas denúncias (para debug/admin)
    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }
}