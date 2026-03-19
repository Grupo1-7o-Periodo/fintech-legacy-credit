package br.com.nogueiranogueira.aularefatoracao.service.template;

import org.springframework.stereotype.Component;

@Component
public class RelatorioCSV extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return "cliente;valor;score;aprovado";
    }

    @Override
    protected String formatarCorpo(String dados) {
        return dados;
    }
}
