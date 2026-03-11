package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;

public interface AnaliseStrategy {
    boolean analisar(SolicitacaoCreditoRequest solicitacao);
}
