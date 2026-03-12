package br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PixStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PixStrategy.class);

    private static final double DESCONTO_PIX = 0.95;

    @Override
    public void pagar(double valor) {
        double valorComDesconto = valor * DESCONTO_PIX;
        log.info("Calculando desconto do PIX...");
        log.info("Gerando chave Copia e Cola.");
        log.info("Pagamento via PIX processado. Total cobrado: R$ {}", valorComDesconto);
    }
}
