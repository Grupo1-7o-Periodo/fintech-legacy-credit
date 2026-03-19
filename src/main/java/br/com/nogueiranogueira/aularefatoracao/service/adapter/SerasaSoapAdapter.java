package br.com.nogueiranogueira.aularefatoracao.service.adapter;

import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class SerasaSoapAdapter implements ServicoAnaliseRisco {

    // URL fictícia de QA (Homologação)
    private static final String SERASA_QA_ENDPOINT = "https://qa.serasa.com.br/ws/ConsultaCredito";

    @Override
    public boolean avaliarCredito(SolicitacaoCredito solicitacao) {
        System.out.println("[Adapter] Iniciando tradução do domínio para SOAP/XML...");

        // 1. TRADUÇÃO DE IDA (Entity -> XML)
        // Uso de Text Blocks (Java 15+) para montar o payload SOAP de forma legível
        String soapPayload = """
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
                """.formatted(solicitacao.getDocumento(), solicitacao.getValor().toString(), solicitacao.getScore());

        try {
            // 2. CHAMADA DE REDE (Usando o HttpClient moderno do Java)
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERASA_QA_ENDPOINT))
                    .header("Content-Type", "text/xml;charset=UTF-8")
                    .header("SOAPAction", "ConsultarRisco") // Cabeçalho obrigatório em SOAP
                    .POST(HttpRequest.BodyPublishers.ofString(soapPayload))
                    .build();

            System.out.println("[Adapter] Enviando requisição para a API Legada...");

            // Simulação de resposta para fins didáticos (como se a API tivesse respondido)
            // Em produção, usaríamos: client.send(request, HttpResponse.BodyHandlers.ofString());
            String xmlRespostaLegada = simularRespostaDaApiExterna();

            // 3. TRADUÇÃO DE VOLTA (XML -> Boolean)
            System.out.println("[Adapter] Traduzindo resposta XML para domínio...");
            return analisarXmlResposta(xmlRespostaLegada);

        } catch (Exception e) {
            System.err.println("Erro na integração com o sistema legado: " + e.getMessage());
            // Em caso de falha da API externa, assumimos uma postura de segurança (Fail-Safe)
            return false;
        }
    }

    private String simularRespostaDaApiExterna() {
        // Simula o que o Serasa devolveria
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

    private boolean analisarXmlResposta(String xml) {
        // Uma extração simples. Num cenário real, usaríamos XPath ou JAXB.
        return xml.contains("<statusConsulta>APROVADO");
    }
}
