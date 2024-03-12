package model.entities;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

    public double taxaRemuneracao;

    public ContaPoupanca(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura,
            double taxaRemuneracao) {
        super(numero, agencia, cliente, saldo, dataAbertura);
        this.taxaRemuneracao = taxaRemuneracao;
    }

    public double getTaxaRemuneracao() {
        return taxaRemuneracao;
    }

    public void atualizaTaxaRemuneracao(int novaTaxaRemuneracao) {
        this.taxaRemuneracao = novaTaxaRemuneracao;
    }

    public void atualizaSaldo() {
        saldo += saldo * taxaRemuneracao;
    }


}
