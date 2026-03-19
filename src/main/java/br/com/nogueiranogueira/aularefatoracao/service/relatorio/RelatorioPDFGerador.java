package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public class RelatorioPDFGerador extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return """
                ========================================
                   RELATÓRIO DE ANÁLISES APROVADAS
                ========================================
                """;
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder sb = new StringBuilder();
        int numero = 1;
        for (String linha : dados) {
            sb.append(numero++).append(". ").append(linha).append("\n");
        }
        sb.append("========================================\n");
        sb.append("Total de registros: ").append(dados.size()).append("\n");
        return sb.toString();
    }
}
