package br.com.nogueiranogueira.aularefatoracao.model.dto;

public record SolicitacaoCreditoRequest(
        String cliente,
        double valor,
        int score,
        boolean negativado,
        TipoConta tipoConta
) {}
