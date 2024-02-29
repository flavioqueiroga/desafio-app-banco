package model.entities;

import java.time.LocalDate;

public class ContaCorrente extends Conta{



    private  Double limite;
    
    public ContaCorrente(Integer numero, Integer agencia, Cliente cliente, Double saldo, LocalDate dataAbertura, Double limite) {
        super(numero, agencia, cliente, saldo, dataAbertura);
        this.limite = limite;
    }
}
