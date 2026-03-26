package br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao;

import org.springframework.stereotype.Component;

@Component
public final class ValidadorCNPJ extends ValidadorDocumentoStrategy {

    @Override
    protected boolean validarFormato(String documento) {
        return documento.length() == 14;
    }

    @Override
    protected boolean validarDigitosVerificadores(String documento) {
        if (documento.chars().distinct().count() == 1) {
            return false;
        }

        int[] pesosPrimeiro = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(documento.charAt(i)) * pesosPrimeiro[i];
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        if (Character.getNumericValue(documento.charAt(12)) != primeiroDigito) {
            return false;
        }

        int[] pesosSegundo = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(documento.charAt(i)) * pesosSegundo[i];
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return Character.getNumericValue(documento.charAt(13)) == segundoDigito;
    }
}
