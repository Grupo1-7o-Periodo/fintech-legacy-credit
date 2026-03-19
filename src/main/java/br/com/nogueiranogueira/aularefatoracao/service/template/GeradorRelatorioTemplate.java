package br.com.nogueiranogueira.aularefatoracao.service.template;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

import java.util.List;

public abstract class GeradorRelatorioTemplate {

    public final String gerarRelatorio(List<SolicitacaoCredito> solicitacoes) {
        String dados = extrairDadosDoBanco(solicitacoes);
        String cabecalho = formatarCabecalho();
        String corpo = formatarCorpo(dados);
        salvarArquivo(cabecalho + "\n" + corpo);
        return cabecalho + "\n" + corpo;
    }

    private String extrairDadosDoBanco(List<SolicitacaoCredito> solicitacoes) {
        StringBuilder sb = new StringBuilder();
        for (SolicitacaoCredito s : solicitacoes) {
            sb.append(s.getCliente()).append(";")
              .append(s.getValor()).append(";")
              .append(s.getScore()).append(";")
              .append(s.getAprovado()).append("\n");
        }
        return sb.toString();
    }

    protected abstract String formatarCabecalho();

    protected abstract String formatarCorpo(String dados);

    private void salvarArquivo(String conteudo) {
        System.out.println("Arquivo salvo com " + conteudo.length() + " caracteres.");
    }
}
