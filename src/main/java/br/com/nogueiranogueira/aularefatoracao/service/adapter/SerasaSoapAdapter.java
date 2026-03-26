package br.com.nogueiranogueira.aularefatoracao.service.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class SerasaSoapAdapter implements ServicoAnaliseRiscoExterno {

    private static final Logger log = LoggerFactory.getLogger(SerasaSoapAdapter.class);

    @Override
    public String avaliarRisco(SolicitacaoCredito solicitacao) {
        String soapRequest = montarRequestSoap(solicitacao);
        log.info("Enviando requisicao SOAP para Serasa: {}", soapRequest);

        String soapResponse = chamarServicoSoap(soapRequest);
        log.info("Resposta SOAP recebida: {}", soapResponse);

        return interpretarRespostaSoap(soapResponse);
    }

    private String montarRequestSoap(SolicitacaoCredito solicitacao) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soapenv:Body>"
                + "<ser:consultarRisco>"
                + "<documento>" + solicitacao.getCliente() + "</documento>"
                + "<valor>" + solicitacao.getValor() + "</valor>"
                + "<score>" + solicitacao.getScore() + "</score>"
                + "</ser:consultarRisco>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
    }

    private String chamarServicoSoap(String soapRequest) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Chamada SOAP interrompida", e);
        }

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soapenv:Body>"
                + "<consultarRiscoResponse>"
                + "<risco>MEDIO</risco>"
                + "<aprovado>true</aprovado>"
                + "</consultarRiscoResponse>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
    }

    private String interpretarRespostaSoap(String soapResponse) {
        if (soapResponse.contains("<risco>")) {
            int inicio = soapResponse.indexOf("<risco>") + 7;
            int fim = soapResponse.indexOf("</risco>");
            return soapResponse.substring(inicio, fim);
        }
        return "INDETERMINADO";
    }
}
