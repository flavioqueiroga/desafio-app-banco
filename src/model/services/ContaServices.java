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

    public static List<Conta> getContasCadastradas(){
        return contasCadastradas;
    }

    public Conta criarContaCC(TipoConta tipo, int numConta, int numAgencia, String nomeCliente, Long cpf,
            LocalDate dataNascimento, double limite) {

        Cliente cliente = criarCliente(nomeCliente, cpf, dataNascimento);
        Conta conta = new ContaCorrente(numConta, numAgencia, cliente, 0.0, LocalDate.now(), limite);

        contasCadastradas.add(conta);

        return conta;

    }

    public Conta criarContaCP(TipoConta tipo, int numConta, int numAgencia, String nomeCliente, Long cpf,
            LocalDate dataNascimento, double taxaRemuneracao) {

        Cliente cliente = criarCliente(nomeCliente, cpf, dataNascimento);
        Conta conta = new ContaPoupanca(numConta, numAgencia, cliente, 0.0, LocalDate.now(), taxaRemuneracao);

        contasCadastradas.add(conta);

        return conta;

    }

    private Cliente criarCliente(String nomeCliente, Long cpf, LocalDate dataNascimento) {
        Cliente cliente = new Cliente(nomeCliente, cpf, dataNascimento);
        return cliente;
    }

    public void alterarLimite(int numeroConta, double novoLimite) {
        Conta conta = contasCadastradas.stream()
            .filter(c -> c.getNumero().equals(numeroConta))
            .findFirst()
            .orElseThrow(() -> new BancoException("Conta não encontrada"));

        if (conta instanceof ContaCorrente)
            ((ContaCorrente)conta).atualizaLimite(novoLimite);
        else
            throw new BancoException("Conta poupança não possui limite!");
    }

    

}