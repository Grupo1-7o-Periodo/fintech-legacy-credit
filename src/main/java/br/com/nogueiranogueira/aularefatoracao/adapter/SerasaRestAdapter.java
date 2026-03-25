package br.com.nogueiranogueira.aularefatoracao.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

public class SerasaRestAdapter extends SerasaAdapterTemplate {

    private static final String SERASA_REST_ENDPOINT = "https://api.serasa.com.br/v1/consulta";

    @Override
    protected String montarPayload(SolicitacaoCredito solicitacao) {
        System.out.println("[REST Adapter] Montando JSON para " + solicitacao.getDocumento());
        return """
                {
                  "documento": "%s",
                  "valorSolicitado": %.2f,
                  "scoreInterno": %d
                }
                """.formatted(solicitacao.getDocumento(), solicitacao.getValor(), solicitacao.getScore());
    }

    @Override
    protected String enviar(String payload) {
        System.out.println("[REST Adapter] Enviando para " + SERASA_REST_ENDPOINT);
        System.out.println("[REST Adapter] Payload:\n" + payload);
        return """
                {
                  "statusConsulta": "APROVADO_REST",
                  "codigoRetorno": "00",
                  "limiteSugerido": 7000.00
                }
                """;
    }

    @Override
    protected boolean interpretarResposta(String resposta) {
        System.out.println("[REST Adapter] Interpretando resposta mockada");
        return resposta.contains("APROVADO");
    }
}
