package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

public sealed interface PagamentoStrategy permits PagamentoPixStrategy, PagamentoCartaoCreditoStrategy, PagamentoBoletoStrategy, PagamentoPaypalStrategy {

    double calcularValorFinal(double valor);

    void processarPagamento(double valor);
}
