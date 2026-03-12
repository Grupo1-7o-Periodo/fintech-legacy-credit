package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.dto.MetodoPagamento;
import br.com.nogueiranogueira.aularefatoracao.factory.PagamentoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    /**
     * BAD SMELLS PRESENTES NESTE CÓDIGO:
     * 1. Violação do OCP (Open/Closed Principle): Para adicionar "CRIPTO", precisamos modificar este método.
     * 2. Violação do SRP (Single Responsibility Principle): A classe valida, calcula descontos/taxas e simula integrações.
     * 3. Magic Numbers: O que é 0.95? O que é 1.05? (Deveriam ser constantes).
     * 4. Código Duplicado: A estrutura de log e impressão se repete muito.
     */
    public void pagar(double valor, MetodoPagamento metodo) {

        log.info("=== Iniciando processamento de pagamento ===");

        PagamentoFactory.obterEstrategia(metodo).pagar(valor);

        log.info("=== Finalizando transação ===");
    }
}