package br.com.nogueiranogueira.aularefatoracao.service.factory;

import br.com.nogueiranogueira.aularefatoracao.model.dto.MetodoPagamento;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.BoletoStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.CartaoCreditoStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.PagamentoStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.PaypalStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.PixStrategy;

public class PagamentoFactory {

    public static PagamentoStrategy obterEstrategia(MetodoPagamento metodo) {
        return switch (metodo) {
            case PIX -> new PixStrategy();
            case CARTAO_CREDITO -> new CartaoCreditoStrategy();
            case PAYPAL -> new PaypalStrategy();
            case BOLETO -> new BoletoStrategy();
        };
    }
}
