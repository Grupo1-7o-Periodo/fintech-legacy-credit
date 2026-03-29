package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public final class AnaliseStrategyCpf implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyCpf.class);

    private static final int SCORE_MINIMO = 500;
    private static final int SCORE_ALTO_VALOR = 800;
    private static final double LIMITE_ALTO_VALOR = 5000.0;

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("Reprovado CPF: cliente negativado");
            return false;
        }

        if (solicitacao.score() <= SCORE_MINIMO) {
            log.warn("Reprovado CPF: score baixo ({})", solicitacao.score());
            return false;
        }

        if (solicitacao.valor() > LIMITE_ALTO_VALOR && solicitacao.score() < SCORE_ALTO_VALOR) {
            log.warn("Reprovado CPF: valor alto com score médio");
            return false;
        }

        if (isFimDeSemana()) {
            log.warn("Reprovado CPF: aprovação manual necessária no fim de semana");
            return false;
        }

        log.info("Aprovado CPF: {}", solicitacao.cliente());
        return true;
    }

    private boolean isFimDeSemana() {
        DayOfWeek dia = LocalDate.now().getDayOfWeek();
        return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
    }
}
