package br.com.nogueiranogueira.aularefatoracao.strategy.documento;

public sealed interface Documento permits Cpf, Cnpj, Curp, Ssn, Nif {

    String valor();
}
