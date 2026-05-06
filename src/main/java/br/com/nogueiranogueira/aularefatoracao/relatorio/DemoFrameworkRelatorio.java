package br.com.nogueiranogueira.aularefatoracao.relatorio;

import br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox.DestinoDisco;
import br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox.FormatoCsv;
import br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox.FormatoPdf;
import br.com.nogueiranogueira.aularefatoracao.relatorio.whitebox.RelatorioCsvWhiteBox;
import br.com.nogueiranogueira.aularefatoracao.relatorio.whitebox.RelatorioPdfWhiteBox;
import org.grupo01.relatorio.blackbox.MotorExportacaoBlackBox;
import org.grupo01.relatorio.model.DadosRelatorio;

import java.util.List;

/**
 * Demonstração das duas abordagens do mini-framework de relatórios.
 * Roda com:
 *   mvn -q exec:java -Dexec.mainClass=...DemoFrameworkRelatorio
 */
public class DemoFrameworkRelatorio {

    public static void main(String[] args) {
        DadosRelatorio dados = new DadosRelatorio(
                "Relatorio Auditoria",
                "2026-05-06",
                List.of(
                        "12345678900 - R$ 100,00 - APROVADO",
                        "98765432100 - R$ 250,50 - REPROVADO"
                )
        );

        System.out.println("\n=== WHITE-BOX (Template Method / herança) ===");
        new RelatorioPdfWhiteBox().exportar(dados);
        new RelatorioCsvWhiteBox().exportar(dados);

        System.out.println("\n=== BLACK-BOX (Strategy / composição + DI) ===");
        new MotorExportacaoBlackBox(new FormatoPdf(), new DestinoDisco()).exportar(dados);
        new MotorExportacaoBlackBox(new FormatoCsv(), new DestinoDisco()).exportar(dados);
    }
}
