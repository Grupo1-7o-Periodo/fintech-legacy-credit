package br.com.nogueiranogueira.aularefatoracao.service.strategy.analise;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;

public interface AnaliseStrategy {

    boolean elegivel(SolicitacaoCreditoRequest solicitacao);

    boolean analisar(SolicitacaoCreditoRequest solicitacao);
}
