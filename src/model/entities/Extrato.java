package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.enums.Operacao;

public class Extrato implements Comparable<Extrato> {

    private Operacao operacao;
    private LocalDateTime data;
    private Double valor;
    private Double saldoMomentaneo;

    public Extrato(Operacao operacao, LocalDateTime data, Double valor, Double saldoPreOperacao) {
        this.operacao = operacao;
        this.data = data;
        this.valor = valor;
        this.saldoMomentaneo = saldoPreOperacao;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Double getValor() {
        return valor;
    }

    public Double getSaldoMomentaneo() {
        return saldoMomentaneo;
    }

    @Override
    public int compareTo(Extrato other) {
        return this.data.compareTo(other.getData());
    }

    @Override
    public String toString() {
        return "Extrato [operacao: " + operacao + ", Data: "
                + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + ", Valor: " + String.format("%.2f", valor)
                + ", Saldo no momento: " + saldoMomentaneo + "]";
    }

    
}
