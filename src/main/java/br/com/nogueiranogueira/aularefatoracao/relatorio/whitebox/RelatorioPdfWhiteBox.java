package br.com.nogueiranogueira.aularefatoracao.relatorio.whitebox;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.grupo01.relatorio.model.DadosRelatorio;
import org.grupo01.relatorio.whitebox.ExportadorRelatorioWhiteBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Extensão White-box: herda do framework e sobrescreve os hooks.
 * O fluxo principal (Template Method) continua no framework.
 */
public class RelatorioPdfWhiteBox extends ExportadorRelatorioWhiteBox {

    @Override
    protected String formatarCabecalho(DadosRelatorio dados) {
        return """
                << FORMATO PDF BOLD >> %s
                ==================================================
                """.formatted(dados.titulo());
    }

    @Override
    protected String formatarCorpo(DadosRelatorio dados) {
        StringBuilder pdf = new StringBuilder();
        for (String linha : dados.linhas()) {
            pdf.append("- Registro Auditado: ").append(linha).append('\n');
        }
        return pdf.toString();
    }

    @Override
    protected void salvarArquivo(String conteudo) {
        Path destino = Paths.get("target", "relatorios", "relatorio.pdf");
        try {
            Files.createDirectories(destino.getParent());
            try (FileOutputStream fos = new FileOutputStream(destino.toFile())) {
                Document document = new Document();
                PdfWriter.getInstance(document, fos);
                document.open();
                document.add(new Paragraph(conteudo));
                document.close();
            }
            System.out.println("[Disco] PDF salvo em: " + destino.toAbsolutePath());
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Falha ao salvar PDF em disco", e);
        }
    }
}
