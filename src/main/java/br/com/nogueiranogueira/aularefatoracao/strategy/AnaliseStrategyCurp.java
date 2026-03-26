package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class AnaliseStrategyCurp implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyCurp.class);

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("Reprovado CURP: cliente negativado");
            return false;
        }

        log.info("Aprovado CURP: {}", solicitacao.cliente());
        return true;
    }
}
