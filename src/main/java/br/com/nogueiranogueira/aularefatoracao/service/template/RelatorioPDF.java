package br.com.nogueiranogueira.aularefatoracao.service.template;

import org.springframework.stereotype.Component;

@Component
public class RelatorioPDF extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return "[PDF] === RELATORIO DE SOLICITACOES DE CREDITO ===";
    }

    @Override
    protected String formatarCorpo(String dados) {
        StringBuilder sb = new StringBuilder();
        String[] linhas = dados.split("\n");
        for (String linha : linhas) {
            if (!linha.isBlank()) {
                String[] campos = linha.split(";");
                sb.append("[PDF] Cliente: ").append(campos[0])
                  .append(" | Valor: R$").append(campos[1])
                  .append(" | Score: ").append(campos[2])
                  .append(" | Aprovado: ").append(campos[3])
                  .append("\n");
            }
        }
        return sb.toString();
    }
}
