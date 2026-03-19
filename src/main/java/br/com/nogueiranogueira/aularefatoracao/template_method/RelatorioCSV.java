package br.com.nogueiranogueira.aularefatoracao.template_method;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RelatorioCSV extends GeradorRelatorioTemplate {
    @Override
    protected String formatarCabecalho() {
        return "cpf,valor,status\n";
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder sb = new StringBuilder();
        for (String linha : dados) {
            sb.append(converterParaCsv(linha)).append('\n');
        }
        return sb.toString();
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
            return linhaBruta.replace(" - ", ",");
        }
        String cpf = partes[0];
        String valor = partes[1].replace("R$", "").trim();
        String status = partes[2];
        return cpf + "," + valor + "," + status;
    }
}
