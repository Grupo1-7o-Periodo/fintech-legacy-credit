package br.com.nogueiranogueira.aularefatoracao.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

public interface ServicoAnaliseRisco {
    public boolean avaliarCredito(SolicitacaoCredito solicitacao);
}
