package br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao;

import org.springframework.stereotype.Component;

@Component
public class ValidadorCPF extends ValidadorDocumentoStrategy {

    @Override
    protected boolean validarFormato(String documento) {
        return documento.length() == 11;
    }

    @Override
    protected boolean validarDigitosVerificadores(String documento) {
        if (documento.chars().distinct().count() == 1) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(documento.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        if (Character.getNumericValue(documento.charAt(9)) != primeiroDigito) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(documento.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return Character.getNumericValue(documento.charAt(10)) == segundoDigito;
    }
}
