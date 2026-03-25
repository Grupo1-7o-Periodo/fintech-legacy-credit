package br.com.nogueiranogueira.aularefatoracao.adapter;

import br.com.nogueiranogueira.aularefatoracao.dto.TipoConta;
import br.com.nogueiranogueira.aularefatoracao.factory.DocumentoValidatorFactory;
import br.com.nogueiranogueira.aularefatoracao.model.SolicitacaoCredito;
import br.com.nogueiranogueira.aularefatoracao.strategy.documento.DocumentoValidatorStrategy;

public abstract class SerasaAdapterTemplate implements ServicoAnaliseRisco {

    private final DocumentoValidatorFactory validatorFactory = new DocumentoValidatorFactory();

    @Override
    public final boolean avaliarCredito(SolicitacaoCredito solicitacao) {
        if (!validarDocumento(solicitacao)) {
            System.out.println("[Adapter] Documento inválido para " + solicitacao.getTipoConta());
            return false;
        }
        String payload = montarPayload(solicitacao);
        String resposta = enviar(payload);
        return interpretarResposta(resposta);
    }

    private boolean validarDocumento(SolicitacaoCredito solicitacao) {
        TipoConta tipo = TipoConta.valueOf(solicitacao.getTipoConta());
        DocumentoValidatorStrategy validator = validatorFactory.obterValidador(tipo);
        return validator.validar(solicitacao.getDocumento());
    }

    protected abstract String montarPayload(SolicitacaoCredito solicitacao);

    protected abstract String enviar(String payload);

    protected abstract boolean interpretarResposta(String resposta);
}
