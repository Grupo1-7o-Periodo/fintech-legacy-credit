package br.com.nogueiranogueira.aularefatoracao.dto;

import br.com.nogueiranogueira.aularefatoracao.strategy.documento.Documento;

public record SolicitacaoCreditoRequest(
        String cliente,
        Documento documento,
        double valor,
        int score,
        boolean negativado,
        TipoConta tipoConta
) {}
