package br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoletoStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(BoletoStrategy.class);

    private static final double TAXA_EMISSAO = 3.50;

    @Override
    public void pagar(double valor) {
        double valorBoleto = valor + TAXA_EMISSAO;
        log.info("Registrando boleto no banco emissor...");
        log.info("Gerando código de barras com vencimento para 3 dias úteis.");
        log.info("Pagamento via Boleto processado. Total cobrado: R$ {}", valorBoleto);
    }
}
