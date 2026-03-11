package br.com.nogueiranogueira.aularefatoracao.controller;

import br.com.nogueiranogueira.aularefatoracao.model.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.service.AnaliseCreditoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Solictações Controller", description = "Endpoints para análise e gerenciamento de solicitações de crédito")
@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoCreditoController {

    private static final Logger log = LoggerFactory.getLogger(SolicitacaoCreditoController.class);

    private final AnaliseCreditoService analiseCreditoService;

    public SolicitacaoCreditoController(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @PostMapping("/analisar")
    public ResponseEntity<Map<String, Object>> analisar(@RequestBody List<SolicitacaoCreditoRequest> solicitacoes) {
        log.info("Recebida requisição para analisar {} solicitação(ões)", solicitacoes.size());

        try {
            analiseCreditoService.processarLote(solicitacoes);

            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Solicitações processadas com sucesso");
            response.put("total", solicitacoes.size());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erro ao analisar solicitações", e);
            Map<String, Object> error = new HashMap<>();
            error.put("erro", "Erro ao processar solicitações");
            error.put("mensagem", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/saude")
    public ResponseEntity<Map<String, String>> saude() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("mensagem", "Aplicação funcionando corretamente");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> analiseCredito(@RequestBody List<SolicitacaoCreditoRecord> solicitacaoCreditoRecords) {
        try {
            processadorAnaliseCreditoService.processarLote(solicitacaoCreditoRecords);
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Solicitações processadas com sucesso");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erro ao processar solicitações", e);
            Map<String, String> error = new HashMap<>();
            error.put("erro", "Erro ao processar solicitações");
            error.put("mensagem", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
