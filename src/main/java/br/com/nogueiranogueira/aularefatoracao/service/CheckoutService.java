package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.service.factory.PagamentoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento.PagamentoStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    public void pagar(double valor, String metodo) {
        if (metodo == null || metodo.trim().isEmpty()) {
            throw new IllegalArgumentException("Método de pagamento não informado!");
        }

        log.info("=== Iniciando processamento de pagamento ===");

        PagamentoStrategy estrategia = PagamentoFactory.obterEstrategia(metodo);
        estrategia.processarPagamento(valor);

        log.info("=== Finalizando transação ===");
    }
}
