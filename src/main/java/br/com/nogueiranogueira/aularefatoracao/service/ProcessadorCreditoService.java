package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;
import br.com.nogueiranogueira.aularefatoracao.service.adapter.ServicoAnaliseRiscoExterno;
import br.com.nogueiranogueira.aularefatoracao.service.factory.AnaliseCreditoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.factory.ValidadorDocumentoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.analise.AnaliseStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao.ValidadorDocumentoStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProcessadorCreditoService {

    private static final Logger log = LoggerFactory.getLogger(ProcessadorCreditoService.class);

    private final ServicoAnaliseRiscoExterno servicoRisco;

    public ProcessadorCreditoService(ServicoAnaliseRiscoExterno servicoRisco) {
        this.servicoRisco = servicoRisco;
    }

    public boolean processarIndividual(SolicitacaoCreditoRequest solicitacao) {
        // Validacao de documento via Strategy + Template Method
        ValidadorDocumentoStrategy validador = ValidadorDocumentoFactory.obterValidador(solicitacao.tipoConta());
        if (!validador.validar(solicitacao.documento())) {
            log.warn("Documento invalido para cliente: {}", solicitacao.cliente());
            return false;
        }

        // Consulta ao Serasa via Adapter
        SolicitacaoCredito model = new SolicitacaoCredito();
        model.setCliente(solicitacao.cliente());
        model.setValor(solicitacao.valor());
        model.setScore(solicitacao.score());
        model.setNegativado(solicitacao.negativado());
        model.setTipoConta(solicitacao.tipoConta().name());

        String risco = servicoRisco.avaliarRisco(model);
        log.info("Risco retornado pelo Serasa para {}: {}", solicitacao.cliente(), risco);

        // Analise de credito via Strategy
        AnaliseStrategy estrategia = AnaliseCreditoFactory.obterEstrategia(solicitacao.tipoConta());
        return estrategia.analisar(solicitacao);
    }

    public void processarLote(List<SolicitacaoCreditoRequest> solicitacoes) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            solicitacoes.forEach(solicitacao ->
                    executor.submit(() -> processarIndividual(solicitacao)));
        }
    }
}
