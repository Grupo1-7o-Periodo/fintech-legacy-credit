package br.com.nogueiranogueira.aularefatoracao.template_method;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RelatorioPDF extends GeradorRelatorioTemplate {
    @Override
    protected String formatarCabecalho() {
        return """
                << FORMATO PDF BOLD >> RELATORIO OFICIAL DE CREDITO
                ==================================================
                """;
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder pdf = new StringBuilder();
        for (String linha : dados) {
            pdf.append("- Registro Auditado: ").append(linha).append("\n");
        }
        return pdf.toString();
    }

    @Override
    protected void salvarArquivo(String conteudo) {
        Path destino = Paths.get("target", "relatorios", "relatorio.pdf");
        try {
            Files.createDirectories(destino.getParent());
            Document document = new Document();
            try (FileOutputStream fos = new FileOutputStream(destino.toFile())) {
                PdfWriter.getInstance(document, fos);
                document.open();
                document.add(new Paragraph(conteudo));
            } finally {
                document.close();
            }
            System.out.println("[Disco] PDF salvo em: " + destino.toAbsolutePath());
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Falha ao salvar PDF em disco", e);
        }
    }
}
