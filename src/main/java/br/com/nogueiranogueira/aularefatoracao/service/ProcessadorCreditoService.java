package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.adapter.ServicoAnaliseRisco;
import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.factory.AnaliseCreditoFactory;
import br.com.nogueiranogueira.aularefatoracao.factory.ServicoAnaliseRiscoFactory;
import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;
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
        ServicoAnaliseRisco servicoExterno = ServicoAnaliseRiscoFactory.obterAdapter(solicitacao.tipoConta());
        boolean aprovadoExterno = servicoExterno.avaliarCredito(mapearParaModelo(solicitacao));
        if (!aprovadoExterno) {
            log.warn("Reprovado pelo serviço externo (documento ou risco): {}", solicitacao.documento());
            return false;
        }

        return AnaliseCreditoFactory.obterEstrategia(solicitacao.tipoConta()).analisar(solicitacao);
    }

    public void processarLote(List<SolicitacaoCreditoRequest> solicitacoes) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            solicitacoes.forEach(solicitacao ->
                    executor.submit(() -> processarIndividual(solicitacao)));
        }
    }

    private SolicitacaoCredito mapearParaModelo(SolicitacaoCreditoRequest request) {
        SolicitacaoCredito solicitacao = new SolicitacaoCredito();
        solicitacao.setCliente(request.cliente());
        solicitacao.setDocumento(request.documento());
        solicitacao.setValor(request.valor());
        solicitacao.setScore(request.score());
        solicitacao.setNegativado(request.negativado());
        solicitacao.setTipoConta(request.tipoConta().name());
        solicitacao.setAprovado(Boolean.FALSE);
        return solicitacao;
    }
}
