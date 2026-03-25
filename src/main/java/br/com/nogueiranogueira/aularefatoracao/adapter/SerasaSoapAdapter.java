package br.com.nogueiranogueira.aularefatoracao.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

public class SerasaSoapAdapter extends SerasaAdapterTemplate {

    private static final String SERASA_QA_ENDPOINT = "https://qa.serasa.com.br/ws/ConsultaCredito";

    @Override
    protected String montarPayload(SolicitacaoCredito solicitacao) {
        System.out.println("[SOAP Adapter] Montando envelope SOAP para " + solicitacao.getDocumento());
        return """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://serasa.com.br/ws">
              <soapenv:Header/>
              <soapenv:Body>
                <ser:ConsultarRisco>
                  <documento>%s</documento>
                  <valorSolicitado>%s</valorSolicitado>
                  <scoreInterno>%d</scoreInterno>
                </ser:ConsultarRisco>
              </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(solicitacao.getDocumento(), solicitacao.getValor(), solicitacao.getScore());
    }

    @Override
    protected String enviar(String payload) {
        System.out.println("[SOAP Adapter] Enviando para " + SERASA_QA_ENDPOINT);
        System.out.println("[SOAP Adapter] Payload:\n" + payload);
        return simularRespostaDaApiExterna();
    }

    @Override
    protected boolean interpretarResposta(String resposta) {
        System.out.println("[SOAP Adapter] Interpretando resposta mockada");
        return resposta.contains("<statusConsulta>APROVADO");
    }

    private String simularRespostaDaApiExterna() {
        return """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
              <soapenv:Body>
                <ConsultarRiscoResponse>
                  <statusConsulta>APROVADO_BAIXO_RISCO</statusConsulta>
                  <codigoRetorno>00</codigoRetorno>
                  <limiteSugerido>5000.00</limiteSugerido>
                </ConsultarRiscoResponse>
              </soapenv:Body>
            </soapenv:Envelope>
            """;
    }
}
