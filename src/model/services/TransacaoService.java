package model.services;

import model.entities.Conta;
import model.exception.BancoException;

public class TransacaoService {



    public void depositar(double valor, Integer numeroConta) {
        Conta conta = ContaServices.getContasCadastradas()
            .stream()
            .filter(c -> c.getNumero().equals(numeroConta))
            .findFirst()
            .orElseThrow(() -> new BancoException("Conta não encontrada"));
        
            conta.deposito(valor);
    }

}
