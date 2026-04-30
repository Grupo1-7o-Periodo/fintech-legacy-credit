package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;

import org.example.documento.interfaces.DocumentoValidatorStrategy;
import org.example.documento.strategies.*;

public class DocumentoValidatorFactory {
    public DocumentoValidatorStrategy obterValidador(TipoConta tipoConta) {
        return switch (tipoConta) {
            case PF -> new CpfValidatorStrategy();
            case PJ -> new CnpjValidatorStrategy();
        };
    }
}
