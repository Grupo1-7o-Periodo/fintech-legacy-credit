package br.com.nogueiranogueira.aularefatoracao.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cpf.class, name = "CPF"),
        @JsonSubTypes.Type(value = Curp.class, name = "CURP"),
        @JsonSubTypes.Type(value = Ssn.class, name = "SSN")
})
public sealed interface Documento permits Cpf, Curp, Ssn {
    String numero();
}
