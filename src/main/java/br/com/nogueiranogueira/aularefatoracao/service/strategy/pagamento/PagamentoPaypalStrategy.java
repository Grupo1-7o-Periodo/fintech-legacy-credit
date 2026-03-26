package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class PagamentoPaypalStrategy implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoPaypalStrategy.class);

    @Override
    public double calcularValorFinal(double valor) {
        return valor;
    }

    @Override
    public void processarPagamento(double valor) {
        double valorFinal = calcularValorFinal(valor);
        log.info("Gerando token de sessão do PayPal...");
        log.info("Redirecionando cliente para a carteira digital.");
        log.info("Pagamento via PayPal processado. Total cobrado: R$ {}", valorFinal);
    }
}
