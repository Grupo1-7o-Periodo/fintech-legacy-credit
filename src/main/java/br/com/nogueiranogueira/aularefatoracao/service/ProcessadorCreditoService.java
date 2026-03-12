package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.service.factory.AnaliseCreditoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.analise.AnaliseStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProcessadorCreditoService {

    private static final Logger log = LoggerFactory.getLogger(ProcessadorCreditoService.class);


    public boolean processarIndividual(SolicitacaoCreditoRequest solicitacao) {
        log.info("Consultando Bureau de Crédito Externo para: {}", solicitacao.cliente());
        consultarBureauExterno();

        AnaliseStrategy analiseCreditoFactory = AnaliseCreditoFactory.obterEstrategia(solicitacao.tipoConta());
        return analiseCreditoFactory.analisar(solicitacao);
    }

    public void processarLote(List<SolicitacaoCreditoRequest> solicitacoes) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            solicitacoes.forEach(solicitacao ->
                    executor.submit(() -> processarIndividual(solicitacao)));
        }
    }

    private void consultarBureauExterno() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Consulta ao bureau interrompida", e);
        }
    }
}
