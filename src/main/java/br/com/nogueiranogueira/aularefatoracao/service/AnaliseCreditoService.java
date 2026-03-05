package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.model.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private static final Logger log = LoggerFactory.getLogger(AnaliseCreditoService.class);

    private final ProcessadorCreditoService processadorCreditoService;

    public AnaliseCreditoService(ProcessadorCreditoService processadorCreditoService) {
        this.processadorCreditoService = processadorCreditoService;
    }

    public void processarLote(List<SolicitacaoCreditoRequest> solicitacoes) {
        log.info("Iniciando análise de {} solicitação(ões)", solicitacoes.size());
        processadorCreditoService.processarLote(solicitacoes);
    }
}
