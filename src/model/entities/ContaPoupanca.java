package model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.enums.Operacao;

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

    public void atualizaTaxaRemuneracao(double novaTaxa) {
        this.taxaRemuneracao = novaTaxa;
    }

    public void aplicarRemuneracao() {
        Double remuneracao = (saldo * taxaRemuneracao) / 100;
        saldo += remuneracao;
        atualizaExtrato(Operacao.REMUNERACAO, LocalDateTime.now(), remuneracao);
    }


}
