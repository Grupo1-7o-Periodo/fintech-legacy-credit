package br.com.nogueiranogueira.aularefatoracao.service.strategy.pagamento;

public interface PagamentoStrategy {

    double calcularValorFinal(double valor);

    void processarPagamento(double valor);
}
