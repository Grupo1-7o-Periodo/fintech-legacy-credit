package br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox;

import org.grupo01.relatorio.blackbox.FormatoRelatorio;
import org.grupo01.relatorio.model.DadosRelatorio;

/**
 * Estratégia Black-box: implementa o contrato do framework para CSV.
 */
public class FormatoCsv implements FormatoRelatorio {

    @Override
    public String formatar(DadosRelatorio dados) {
        StringBuilder sb = new StringBuilder("documento;valor;status\n");
        for (String linha : dados.linhas()) {
            sb.append(converterParaCsv(linha)).append('\n');
        }
        return sb.toString();
    }

    @Override
    public String extensao() {
        return "csv";
    }

    private String converterParaCsv(String linhaBruta) {
        String[] partes = linhaBruta.split(" - ");
        if (partes.length != 3) {
            return linhaBruta.replace(" - ", ";");
        }
        String documento = partes[0];
        String valor = partes[1].replace("R$", "").trim();
        String status = partes[2];
        return documento + ";" + valor + ";" + status;
    }
}
