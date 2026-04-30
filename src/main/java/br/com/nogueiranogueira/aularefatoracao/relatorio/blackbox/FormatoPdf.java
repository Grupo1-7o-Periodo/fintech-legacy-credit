package br.com.nogueiranogueira.aularefatoracao.relatorio.blackbox;

import org.grupo01.relatorio.blackbox.FormatoRelatorio;
import org.grupo01.relatorio.model.DadosRelatorio;

/**
 * Estratégia Black-box: implementa o contrato do framework para PDF.
 * O motor não conhece esta classe — apenas a interface {@link FormatoRelatorio}.
 */
public class FormatoPdf implements FormatoRelatorio {

    @Override
    public String formatar(DadosRelatorio dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("<< FORMATO PDF BOLD >> ").append(dados.titulo()).append('\n');
        sb.append("==================================================\n");
        for (String linha : dados.linhas()) {
            sb.append("- Registro Auditado: ").append(linha).append('\n');
        }
        return sb.toString();
    }

    @Override
    public String extensao() {
        return "pdf";
    }
}
