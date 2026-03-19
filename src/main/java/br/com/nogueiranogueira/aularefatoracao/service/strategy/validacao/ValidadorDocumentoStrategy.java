package br.com.nogueiranogueira.aularefatoracao.service.strategy.validacao;

public abstract class ValidadorDocumentoStrategy {

    public final boolean validar(String documento) {
        String documentoLimpo = limparDocumento(documento);

        if (!validarFormato(documentoLimpo)) {
            return false;
        }

        return validarDigitosVerificadores(documentoLimpo);
    }

    private String limparDocumento(String documento) {
        return documento.replaceAll("[^0-9]", "");
    }

    protected abstract boolean validarFormato(String documento);

    protected abstract boolean validarDigitosVerificadores(String documento);
}
