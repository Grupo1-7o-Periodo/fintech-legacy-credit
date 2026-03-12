package br.com.nogueiranogueira.aularefatoracao.service.factory;

import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoBoletoStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoCartaoCreditoStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoPaypalStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoPixStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoStrategy;

public class PagamentoFactory {

    public static PagamentoStrategy obterEstrategia(String metodo) {
        return switch (metodo.toUpperCase()) {
            case "PIX" -> new PagamentoPixStrategy();
            case "CARTAO_CREDITO" -> new PagamentoCartaoCreditoStrategy();
            case "PAYPAL" -> new PagamentoPaypalStrategy();
            case "BOLETO" -> new PagamentoBoletoStrategy();
            default -> throw new IllegalArgumentException("Método de pagamento não suportado: " + metodo);
        };
    }
}
