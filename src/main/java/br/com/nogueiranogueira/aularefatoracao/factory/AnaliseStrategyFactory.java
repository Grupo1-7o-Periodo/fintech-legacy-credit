package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategy;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategyCnpj;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategyCpf;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategyCurp;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategyNif;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategySsn;
import org.example.documento.interfaces.*;

public class AnaliseStrategyFactory {
    public AnaliseStrategy obterEstrategia(Documento documento) {
        return switch (documento) {
            case Cpf cpf -> new AnaliseStrategyCpf();
            case Cnpj cnpj -> new AnaliseStrategyCnpj();
            case Curp curp -> new AnaliseStrategyCurp();
            case Nif nif -> new AnaliseStrategyNif();
            case Ssn ssn -> new AnaliseStrategySsn();
        };
    }
}
