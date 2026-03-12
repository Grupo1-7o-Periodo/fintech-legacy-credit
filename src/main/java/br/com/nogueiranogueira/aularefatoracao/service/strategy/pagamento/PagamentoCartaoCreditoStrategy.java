package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoCartaoCreditoStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoCartaoCreditoStrategy.class);

    private static final double FATOR_ACRESCIMO = 1.05;

    @Override
    public double calcularValorFinal(double valor) {
        return valor * FATOR_ACRESCIMO;
    }

    @Override
    public void processarPagamento(double valor) {
        double valorFinal = calcularValorFinal(valor);
        log.info("Conectando com a adquirente (Cielo/Rede)...");
        log.info("Validando limite e risco de fraude.");
        log.info("Pagamento via Cartão processado. Total cobrado: R$ {}", valorFinal);
    }
}
