package br.com.nogueiranogueira.aularefatoracao.model.dto;

public record SolicitacaoCreditoRequest(
        String cliente,
        String documento,
        double valor,
        int score,
        boolean negativado,
        TipoConta tipoConta
) {}
