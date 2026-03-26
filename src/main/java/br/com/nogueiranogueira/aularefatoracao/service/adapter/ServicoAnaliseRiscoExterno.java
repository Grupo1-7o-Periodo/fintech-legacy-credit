package br.com.nogueiranogueira.aularefatoracao.service.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

public sealed interface ServicoAnaliseRiscoExterno permits SerasaSoapAdapter {

    String avaliarRisco(SolicitacaoCredito solicitacao);
}
