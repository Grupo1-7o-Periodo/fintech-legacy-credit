package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.adapter.SerasaRestAdapter;
import br.com.nogueiranogueira.aularefatoracao.adapter.SerasaSoapAdapter;
import br.com.nogueiranogueira.aularefatoracao.adapter.ServicoAnaliseRisco;
import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;

public class ServicoAnaliseRiscoFactory {

    private ServicoAnaliseRiscoFactory() {}

    public static ServicoAnaliseRisco obterAdapter(TipoConta tipoConta) {
        // Exemplo simples: PF via REST, PJ via SOAP (mockado)
        return switch (tipoConta) {
            case PF -> new SerasaRestAdapter();
            case PJ -> new SerasaSoapAdapter();
        };
    }
}
