package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.Cliente;
import model.entities.Conta;
import model.entities.ContaCorrente;
import model.entities.ContaPoupanca;
import model.enums.TipoConta;
import model.exception.BancoException;

public final class ContaServices {

    private static List<Conta> contasCadastradas = new ArrayList<Conta>();


    public static Conta obterConta(int numeroConta) {
        return contasCadastradas.stream()
                .filter(c -> c.getNumero().equals(numeroConta))
                .findFirst()
                .orElseThrow(() -> new BancoException("Conta não encontrada"));
    }

    public static boolean contaExiste(int numeroConta) {
        return contasCadastradas.stream()
                .anyMatch(c -> c.getNumero().equals(numeroConta));
    }

    public Conta criarContaCC(TipoConta tipo, int numConta, int numAgencia, Cliente cliente, double limite) {
        if (contaExiste(numConta)){
            throw new BancoException("Já existe uma conta com este nome");
        }
        Conta conta = new ContaCorrente(numConta, numAgencia, cliente, 0.0, LocalDate.now(), limite);
        contasCadastradas.add(conta);

        return conta;

    }

    public Conta criarContaCP(TipoConta tipo, int numConta, int numAgencia, Cliente cliente, double taxaRemuneracao) {
        if (contaExiste(numConta)){
            throw new BancoException("Já existe uma conta com este número.");
        }
        Conta conta = new ContaPoupanca(numConta, numAgencia, cliente, 0.0, LocalDate.now(), taxaRemuneracao);
        contasCadastradas.add(conta);

        return conta;
    }



    public void alterarLimite(int numeroConta, double novoLimite) {
        Conta conta = obterConta(numeroConta);

        if (conta instanceof ContaCorrente)
            ((ContaCorrente) conta).atualizaLimite(novoLimite);
        else
            throw new BancoException("Conta poupança não possui limite!");

        
    }

    public void aplicarTaxaRemuneracao(int numeroConta) {
        Conta conta = obterConta(numeroConta);

        if (conta instanceof ContaPoupanca)
            ((ContaPoupanca) conta).aplicarRemuneracao();
        else
            throw new BancoException("A remuneração não pode ser aplicada a uma Conta corrente!");
    }

}
