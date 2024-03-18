package model.services;

import java.time.LocalDateTime;

import model.entities.Conta;
import model.exception.BancoException;

public class TransacaoServices {

    private static final int HORA_LIMITE_DE = 20;
    private static final int HORA_LIMITE_ATE = 6;
    private static final Double VALOR_LIMITE_HORARIO = 500.0; 

    public void depositar(double valor, int numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        conta.deposito(valor);
    }

    public void saque(double valor, int numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        conta.saque(valor);
    }

    public void transferir(int numContaOrigem, int numContaDestino, double valor) {
        if (numContaDestino == numContaOrigem){
            throw new BancoException("Não é permitido executar transferencias para a mesma conta.");
        }
        int hora = LocalDateTime.now().getHour();

        if (valor > VALOR_LIMITE_HORARIO && (hora > 20 || hora < 6)){
            throw new BancoException("As transferencias de valores acima de R$ " 
                + VALOR_LIMITE_HORARIO +  " só podem ser executadas Até às " 
                + HORA_LIMITE_DE 
                + ":00 horas, ou a partir das " 
                + HORA_LIMITE_ATE + ":00 horas.");
        }

        Conta contaOrigem = ContaServices.obterConta(numContaOrigem);
        if (valor > contaOrigem.obterSaldoTransacao()) {
            throw new BancoException("Saldo insuficiente para efetuar a transação");
        }
        Conta contaDestino = ContaServices.obterConta(numContaDestino);
        contaOrigem.saque(valor);
        contaDestino.deposito(valor);
      
    }

}
