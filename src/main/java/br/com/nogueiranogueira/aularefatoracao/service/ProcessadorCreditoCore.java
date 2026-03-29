package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.factory.AnaliseStrategyFactory;

public class ProcessadorCreditoCore {

    private final AnaliseStrategyFactory analiseStrategyFactory;

    public ProcessadorCreditoCore() {
        this(new AnaliseStrategyFactory());
    }

    public ProcessadorCreditoCore(AnaliseStrategyFactory analiseStrategyFactory) {
        this.analiseStrategyFactory = analiseStrategyFactory;
    }

    public boolean processar(SolicitacaoCreditoRequest solicitacao) {
        return analiseStrategyFactory.obterEstrategia(solicitacao.documento())
                .analisar(solicitacao);
    }
}
