package br.com.nogueiranogueira.aularefatoracao.service.factory;

import br.com.nogueiranogueira.aularefatoracao.model.dto.TipoConta;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.credito.AnaliseStrategy;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.credito.AnaliseStrategyPF;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.credito.AnaliseStrategyPJ;

public class AnaliseCreditoFactory {

    // Método estático que devolve a interface (Abstração), nunca a classe concreta
    public static AnaliseStrategy obterEstrategia(TipoConta tipo) {

        // Java 21: Switch Expression com "Arrow syntax" (->)
        // Não precisa de 'break' e o valor é retornado diretamente.
        return switch (tipo) {
            case PF -> new AnaliseStrategyPF();
            case PJ -> new AnaliseStrategyPJ();

            // O compilador do Java 21 obriga a cobrir todos os cenários.
            // Se adicionarmos um novo TipoConta no Enum e esquecermos de atualizar a Factory, o código nem compila!
            default -> throw new IllegalArgumentException("Tipo de conta não suportado: " + tipo);
        };
    }
}