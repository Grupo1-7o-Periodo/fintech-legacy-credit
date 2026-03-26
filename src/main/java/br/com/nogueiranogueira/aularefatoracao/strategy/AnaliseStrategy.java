package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;


public sealed interface AnaliseStrategy
        permits AnaliseStrategyCpf, AnaliseStrategyCnpj, AnaliseStrategyCurp, AnaliseStrategySsn, AnaliseStrategyNif {

    boolean analisar(SolicitacaoCreditoRequest solicitacao);
}
