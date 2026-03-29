package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class AnaliseStrategyNif implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyNif.class);

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("Reprovado NIF: cliente negativado");
            return false;
        }

        log.info("Aprovado NIF: {}", solicitacao.cliente());
        return true;
    }
}
