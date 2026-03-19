package br.com.nogueiranogueira.aularefatoracao.service.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

public interface ServicoAnaliseRiscoExterno {

    String avaliarRisco(SolicitacaoCredito solicitacao);
}
