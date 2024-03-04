package model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public abstract class Conta {

    private Integer numero;
    private Integer agencia;
    private Cliente cliente;
    protected Double saldo;
    private LocalDate dataAbertura;
    private Set<Extrato> extrato = new TreeSet<>();

    public Conta(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura) {
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void saque(Double valor) {
        saldo -= valor;
    }

    public void deposito(Double valor) {
        saldo -= valor;
    }

    public void atualizaEztrato(Operacao op, LocalDateTime data, Double valor){
        extrato.add(new Extrato(op, data, valor, this.saldo));
    }

    

}
