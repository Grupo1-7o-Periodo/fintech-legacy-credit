package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoBoletoStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoBoletoStrategy.class);

    private static final double TAXA_EMISSAO = 3.50;

    @Override
    public double calcularValorFinal(double valor) {
        return valor + TAXA_EMISSAO;
    }

    @Override
    public void processarPagamento(double valor) {
        double valorFinal = calcularValorFinal(valor);
        log.info("Registrando boleto no banco emissor...");
        log.info("Gerando código de barras com vencimento para 3 dias úteis.");
        log.info("Pagamento via Boleto processado. Total cobrado: R$ {}", valorFinal);
    }
}
