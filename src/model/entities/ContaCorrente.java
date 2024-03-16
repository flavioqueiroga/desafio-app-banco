package model.entities;

import java.time.LocalDate;

import model.exception.BancoException;

public class ContaCorrente extends Conta{



    private  Double limite;
    
    public ContaCorrente(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura, Double limite) {
        super(numero, agencia, cliente, saldo, dataAbertura);
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }

    public void atualizaLimite(double novoLimite){
        if (Math.abs(saldo) > novoLimite){
            throw new BancoException("Esse limite é menor do que saldo atual negativo da conta. Saldo atual: R$ " + String.format("%.2f", saldo));
        }
        this.limite = novoLimite;
    }

    @Override
    public String toString() {
        return "ContaCorrente [limite=" + limite + "]";
    }

    @Override
    public Double obterSaldoTransacao() {
        return saldo + limite;
    }



    

    
}
