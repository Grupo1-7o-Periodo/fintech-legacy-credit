package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;
import br.com.nogueiranogueira.aularefatoracao.strategy.documento.CnpjValidatorStrategy;
import br.com.nogueiranogueira.aularefatoracao.strategy.documento.CpfValidatorStrategy;
import br.com.nogueiranogueira.aularefatoracao.strategy.documento.DocumentoValidatorStrategy;

public class DocumentoValidatorFactory {
    public DocumentoValidatorStrategy obterValidador(TipoConta tipoConta) {
        return switch (tipoConta) {
            case PF -> new CpfValidatorStrategy();
            case PJ -> new CnpjValidatorStrategy();
        };
    }
}
