package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public class RelatorioCSVGerador extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return "documento,valor,status\n";
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder sb = new StringBuilder();
        for (String linha : dados) {
            // Converte "DOC - R$ VALOR - STATUS" para formato CSV
            String csv = linha
                    .replace(" - R$ ", ",")
                    .replace(" - ", ",");
            sb.append(csv).append("\n");
        }
        return sb.toString();
    }
}
