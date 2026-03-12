package br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartaoCreditoStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(CartaoCreditoStrategy.class);

    private static final double TAXA_CONVENIENCIA = 1.05;

    @Override
    public void pagar(double valor) {
        double valorComAcrescimo = valor * TAXA_CONVENIENCIA;
        log.info("Conectando com a adquirente (Cielo/Rede)...");
        log.info("Validando limite e risco de fraude.");
        log.info("Pagamento via Cartão de Crédito processado. Total cobrado: R$ {}", valorComAcrescimo);
    }
}
