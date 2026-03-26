package br.com.nogueiranogueira.aularefatoracao.strategy.documento;

public sealed interface Documento permits Cpf, Cnpj, Rg, Curp, Ssn {

    String valor();
}
