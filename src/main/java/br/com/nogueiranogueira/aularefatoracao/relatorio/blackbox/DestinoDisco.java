package br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox;

import org.grupo01.relatorio.blackbox.DestinoRelatorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Estratégia Black-box: grava o conteúdo já formatado em disco
 * (target/relatorios/&lt;nome&gt;).
 */
public class DestinoDisco implements DestinoRelatorio {

    @Override
    public void gravar(String nomeArquivo, String conteudo) {
        Path destino = Paths.get("target", "relatorios", nomeArquivo);
        try {
            Files.createDirectories(destino.getParent());
            Files.writeString(destino, conteudo);
            System.out.println("[Disco] Arquivo salvo em: " + destino.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar arquivo em disco", e);
        }
    }
}
