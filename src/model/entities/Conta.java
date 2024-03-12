package model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import model.enums.Operacao;
import model.exception.BancoException;

public abstract class Conta {

    private Integer numero;
    private Integer agencia;
    private Cliente cliente;
    protected Double saldo;
    private LocalDate dataAbertura;
    private Set<Extrato> extrato = new TreeSet<>();

    public Conta(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura) {
        if (saldo < 0){
            throw new BancoException("Não é possível criar uma conta com saldo negativo.");
        }
        
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void saque(Double valor) {
        if (valor <= 0)
            throw new BancoException("Informe um valor positivo para efetuar o saque");
        saldo -= valor;
    }

    public void deposito(Double valor) {
        if (valor <= 0)
            throw new BancoException("Não é possivel efetuar deposito de valor menor ou igual a zero");
        saldo += valor;
    }

    public void atualizaExtrato(Operacao op, LocalDateTime data, Double valor) {
        extrato.add(new Extrato(op, data, valor, this.saldo));
    }

}
