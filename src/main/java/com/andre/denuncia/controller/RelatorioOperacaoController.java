package com.andre.denuncia.controller;
import com.andre.denuncia.model.RelatorioOperacao;
import com.andre.denuncia.repository.DenunciaRepository;
import com.andre.denuncia.repository.RelatorioOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioOperacaoController {

    @Autowired
    private RelatorioOperacaoRepository relatorioRepo;
    @Autowired
    private DenunciaRepository denunciaRepo;

    @PostMapping("/{denunciaId}/anexar-foto")
    public RelatorioOperacao anexarFoto(
            @PathVariable Long denunciaId,
            @RequestParam String relatorio,
            @RequestParam("file") MultipartFile file) throws Exception {
        RelatorioOperacao rel = new RelatorioOperacao();
        rel.setDenuncia(denunciaRepo.findById(denunciaId).orElseThrow());
        rel.setRelatorio(relatorio);
        String pasta = "uploads/fotos/";
        Files.createDirectories(Paths.get(pasta));
        String fileName = pasta + denunciaId + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        rel.setFotoPath(fileName);
        rel.setDataHora(java.time.LocalDateTime.now());
        return relatorioRepo.save(rel);
    }
}
