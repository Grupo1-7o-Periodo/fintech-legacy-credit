package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;
import br.com.nogueiranogueira.aularefatoracao.model.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.service.factory.AnaliseCreditoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.credito.AnaliseStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ProcessadorCreditoService {

    private static final Logger log = LoggerFactory.getLogger(ProcessadorCreditoService.class);

    public SolicitacaoCredito processarIndividual(SolicitacaoCreditoRequest solicitacao) {
        log.info("Consultando Bureau de Crédito Externo para: {}", solicitacao.cliente());
        consultarBureauExterno();

        AnaliseStrategy strategy = AnaliseCreditoFactory.obterEstrategia(solicitacao.tipoConta());
        boolean aprovado = strategy.analisar(solicitacao);

        SolicitacaoCredito resultado = new SolicitacaoCredito();
        resultado.setCliente(solicitacao.cliente());
        resultado.setDocumento(solicitacao.documento());
        resultado.setValor(solicitacao.valor());
        resultado.setScore(solicitacao.score());
        resultado.setNegativado(solicitacao.negativado());
        resultado.setTipoConta(solicitacao.tipoConta().name());
        resultado.setAprovado(aprovado);

        return resultado;
    }

    public List<SolicitacaoCredito> processarLote(List<SolicitacaoCreditoRequest> solicitacoes) {
        List<Future<SolicitacaoCredito>> futures = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (SolicitacaoCreditoRequest solicitacao : solicitacoes) {
                futures.add(executor.submit(() -> processarIndividual(solicitacao)));
            }
        } // close() aguarda todas as tasks finalizarem (Java 21)

        return futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        log.error("Erro ao processar solicitação", e);
                        throw new RuntimeException(e);
                    }
                })
                .toList();
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
