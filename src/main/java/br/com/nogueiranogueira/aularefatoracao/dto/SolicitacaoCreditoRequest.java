package br.com.nogueiranogueira.aularefatoracao.dto;

import org.example.documento.interfaces.Documento;

public record SolicitacaoCreditoRequest(
        String cliente,
        Documento documento,
        double valor,
        int score,
        boolean negativado,
        TipoConta tipoConta
) {}
