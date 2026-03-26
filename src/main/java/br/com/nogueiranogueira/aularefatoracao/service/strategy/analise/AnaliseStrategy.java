package br.com.nogueiranogueira.aularefatoracao.service.strategy.analise;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;

public sealed interface AnaliseStrategy permits AnaliseStrategyPF, AnaliseStrategyPJ {

    boolean elegivel(SolicitacaoCreditoRequest solicitacao);

    boolean analisar(SolicitacaoCreditoRequest solicitacao);
}
