package model.services;

import model.entities.Conta;
import model.exception.BancoException;

public class TransacaoService {

    public void depositar(double valor, Integer numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        conta.deposito(valor);
    }

    public void saque(double valor, int numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        conta.saque(valor);
    }

    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {

        Conta contaOrigem = ContaServices.obterConta(numeroContaOrigem);
        if (valor > contaOrigem.obterSaldoTransacao()) {
            throw new BancoException("Saldo insuficiente para efetuar a transação");
        }

        Conta contaDestino = ContaServices.obterConta(numeroContaDestino);
        contaOrigem.saque(valor);
        contaDestino.deposito(valor);

    }

}
