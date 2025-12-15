package com.andre.denuncia.controller;

import com.andre.denuncia.model.Denuncia;
import com.andre.denuncia.model.RelatorioOperacao;
import com.andre.denuncia.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    // Cidadão cria denúncia
    @PostMapping
    public Denuncia criarDenuncia(@RequestBody Denuncia denuncia) {
        return denunciaService.salvar(denuncia);
    }

    // Agente lista denúncias do tipo
    @GetMapping("/tipo/{tipoAgente}")
    public List<Denuncia> listarPorTipo(@PathVariable String tipoAgente) {
        return denunciaService.listarPorTipo(tipoAgente);
    }

    // Agente marca como "Recebido"
    @PutMapping("/{id}/receber")
    public Denuncia marcarRecebido(@PathVariable Long id, @RequestParam Long agenteId) {
        return denunciaService.marcarRecebido(id, agenteId);
    }

    // Agente finaliza denúncia com relatório
    @PutMapping("/{id}/finalizar")
    public Denuncia finalizarDenuncia(@PathVariable Long id, @RequestBody RelatorioOperacao relatorio) {
        return denunciaService.finalizar(id, relatorio);
    }

    // Listar todas denúncias (admin/teste)
    @GetMapping
    public List<Denuncia> listarTodas() {
        return denunciaService.listarTodas();
    }

    @PostMapping("/audio/{denunciaId}")
    public Denuncia enviarAudio(
            @PathVariable Long denunciaId,
            @RequestParam("file") MultipartFile file) throws Exception {
        Denuncia denuncia = denunciaService.buscarPorId(denunciaId);
        if (denuncia == null) throw new RuntimeException("Denúncia não encontrada.");
        // Salvar arquivo
        String pasta = "uploads/audio/";
        Files.createDirectories(Paths.get(pasta));
        String fileName = pasta + denunciaId + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        denuncia.setAudioPath(fileName);
        return denunciaService.salvar(denuncia);
    }
}

