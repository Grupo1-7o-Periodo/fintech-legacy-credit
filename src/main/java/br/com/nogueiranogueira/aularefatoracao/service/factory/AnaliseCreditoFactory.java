package br.com.nogueiranogueira.aularefatoracao.service.factory;

import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.analise.AnaliseStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.analise.AnaliseStrategyPF;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.analise.AnaliseStrategyPJ;

public class AnaliseCreditoFactory {
    // Método estático que devolve a interface (Abstração), nunca a classe concreta
    public static AnaliseStrategy obterEstrategia(TipoConta tipo) {
        return switch (tipo) {
            case PF -> new AnaliseStrategyPF();
            case PJ -> new AnaliseStrategyPJ();
        };
    }
}