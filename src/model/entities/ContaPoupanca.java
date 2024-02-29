package model.entities;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

    public int taxaRemuneracao;

    public ContaPoupanca(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura,
            int taxaRemuneracao) {
        super(numero, agencia, cliente, saldo, dataAbertura);
        this.taxaRemuneracao = taxaRemuneracao;
    }

    public int getTaxaRemuneracao() {
        return taxaRemuneracao;
    }

    public void setTaxaRemuneracao(int taxaRemuneracao) {
        this.taxaRemuneracao = taxaRemuneracao;
    }

    public void atualizaSaldo() {
        saldo += saldo * taxaRemuneracao;
    }
}
