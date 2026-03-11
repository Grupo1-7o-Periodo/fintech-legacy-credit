package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AnaliseStrategyPJ implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyPJ.class);

    private static final int SCORE_MINIMO = 500;
    private static final int SCORE_ALTO_VALOR = 700;
    private static final double LIMITE_ALTO_VALOR = 50000.0;

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("Reprovado PJ: cliente negativado");
            return false;
        }

        if (solicitacao.score() <= SCORE_MINIMO) {
            log.warn("Reprovado PJ: score baixo ({})", solicitacao.score());
            return false;
        }

        if (solicitacao.valor() > LIMITE_ALTO_VALOR && solicitacao.score() < SCORE_ALTO_VALOR) {
            log.warn("Reprovado PJ: risco alto para valor elevado com score insuficiente");
            return false;
        }

        log.info("Aprovado PJ: {}", solicitacao.cliente());
        return true;
    }
}
