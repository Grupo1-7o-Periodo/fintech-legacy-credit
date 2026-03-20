package br.com.nogueiranogueira.aularefatoracao.strategy.documento;

public class CnpjValidatorStrategy implements DocumentoValidatorStrategy {
    @Override
    public boolean validar(String documento) {
        if (documento == null) {
            return false;
        }
        String digits = documento.replaceAll("\\D", "");
        if (digits.length() != 14) {
            return false;
        }
        return !digits.chars().allMatch(c -> c == digits.charAt(0));
    }
}
