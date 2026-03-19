package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public abstract class GeradorRelatorioTemplate {

    // Método final: define o esqueleto do algoritmo e não pode ser sobrescrito
    public final void gerarRelatorio(String dataReferencia) {
        System.out.println("--- Iniciando Geração de Relatório para: " + dataReferencia + " ---");

        List<String> dados = extrairDadosDoBanco();

        if (dados.isEmpty()) {
            System.out.println("Sem dados para exportar.");
            return;
        }

        String cabecalho = formatarCabecalho();
        String corpo = formatarCorpo(dados);
        salvarArquivo(cabecalho + corpo);
    }

    // Passos fixos (comuns a todos os relatórios)
    private List<String> extrairDadosDoBanco() {
        System.out.println("[Banco] Executando SELECT * FROM analises_credito...");
        return List.of(
                "123.456.789-00 - R$ 5000 - APROVADO",
                "987.654.321-11 - R$ 1000 - APROVADO"
        );
    }

    private void salvarArquivo(String conteudo) {
        System.out.println("[Disco] Salvando ficheiro na rede...\n" + conteudo);
    }

    // Passos variáveis (delegados às subclasses)
    protected abstract String formatarCabecalho();

    protected abstract String formatarCorpo(List<String> dados);
}
