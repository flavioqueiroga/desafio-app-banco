package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.entities.Conta;
import model.entities.Extrato;
import model.enums.Operacao;
import model.exception.BancoException;

public class ExtratoServices {

    public double filtroDoExtrato(Set<Extrato> extrato, Predicate<Extrato> criteria) {
        double total = 0.0;
        for (Extrato e : extrato) {
            if (criteria.test(e)) {
                total += e.getValor();
            }
        }
        return total;
    }

    public Set<Extrato> obterExtratoImpressao(int numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        return conta.getExtrato();
    }

    public void gerarExtratoCSV(int numeroConta) {
        Conta conta = ContaServices.obterConta(numeroConta);
        String header = "Operacao, Data, Valor, Saldo";

        String path = "extrato-conta-" + conta.getNumero() + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(header);
            for (Extrato ex : conta.getExtrato()) {
                bw.newLine();
                bw.write(ex.getOperacao() + ", " + ex.getData() + ", " 
                        + ex.getValor() + ", " + ex.getSaldoMomentaneo());
            }
        } catch (Exception e) {
            throw new BancoException("Erro ao escrever o extrato: " + e.getMessage());
        }
    }

    public Set<Extrato> obterExtratoResumido(int numeroConta, Operacao op) {
        Conta conta = ContaServices.obterConta(numeroConta);
        return conta.getExtrato()
            .stream()
            .filter(e -> e.getOperacao() == op)
            .collect(Collectors.toSet());
    } 

    

}
