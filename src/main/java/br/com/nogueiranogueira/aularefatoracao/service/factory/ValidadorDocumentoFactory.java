package br.com.nogueiranogueira.aularefatoracao.service.factory;

import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao.ValidadorCNPJ;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao.ValidadorCPF;
import br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao.ValidadorDocumentoStrategy;

public class ValidadorDocumentoFactory {

    public static ValidadorDocumentoStrategy obterValidador(TipoConta tipo) {
        return switch (tipo) {
            case PF -> new ValidadorCPF();
            case PJ -> new ValidadorCNPJ();
            default -> throw new IllegalArgumentException("Tipo de conta nao suportado: " + tipo);
        };
    }
}
