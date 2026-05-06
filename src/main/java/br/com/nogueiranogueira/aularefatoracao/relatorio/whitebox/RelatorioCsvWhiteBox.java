package br.com.nogueiranogueira.aularefatoracao.relatorio.whitebox;

import org.grupo01.relatorio.model.DadosRelatorio;
import org.grupo01.relatorio.whitebox.ExportadorRelatorioWhiteBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Extensão White-box para CSV: herda do framework e sobrescreve os hooks.
 */
public class RelatorioCsvWhiteBox extends ExportadorRelatorioWhiteBox {

    @Override
    protected String formatarCabecalho(DadosRelatorio dados) {
        return "documento;valor;status\n";
    }

    @Override
    protected String formatarCorpo(DadosRelatorio dados) {
        StringBuilder sb = new StringBuilder();
        for (String linha : dados.linhas()) {
            sb.append(converterParaCsv(linha)).append('\n');
        }
        return sb.toString();
    }

    @Override
    protected String formatarRodape(DadosRelatorio dados) {
        // CSV não tem rodapé textual: sobrescreve o hook default do framework.
        return "";
    }

    @Override
    protected void salvarArquivo(String conteudo) {
        Path destino = Paths.get("target", "relatorios", "relatorio.csv");
        try {
            Files.createDirectories(destino.getParent());
            Files.writeString(destino, conteudo);
            System.out.println("[Disco] CSV salvo em: " + destino.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar CSV em disco", e);
        }
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
