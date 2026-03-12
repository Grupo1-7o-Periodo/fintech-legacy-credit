package br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaypalStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PaypalStrategy.class);

    @Override
    public void pagar(double valor) {
        log.info("Gerando token de sessão do PayPal...");
        log.info("Redirecionando cliente para a carteira digital.");
        log.info("Pagamento via PayPal processado. Total cobrado: R$ {}", valor);
    }
}
