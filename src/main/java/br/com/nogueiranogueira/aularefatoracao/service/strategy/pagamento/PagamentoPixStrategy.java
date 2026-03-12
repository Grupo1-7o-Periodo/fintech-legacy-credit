package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPixStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoPixStrategy.class);

    private static final double FATOR_DESCONTO = 0.95;

    @Override
    public double calcularValorFinal(double valor) {
        return valor * FATOR_DESCONTO;
    }

    @Override
    public void processarPagamento(double valor) {
        double valorFinal = calcularValorFinal(valor);
        log.info("Calculando desconto do PIX...");
        log.info("Gerando chave Copia e Cola.");
        log.info("Pagamento via PIX processado. Total cobrado: R$ {}", valorFinal);
    }
}
