package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.model.dto.MetodoPagamento;
import br.com.nogueiranogueira.aularefatoracao.service.factory.PagamentoFactory;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.checkout.PagamentoStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    public void pagar(double valor, MetodoPagamento metodo) {
        log.info("=== Iniciando processamento de pagamento ===");

        PagamentoStrategy estrategia = PagamentoFactory.obterEstrategia(metodo);
        estrategia.pagar(valor);

        log.info("=== Finalizando transação ===");
    }
}
