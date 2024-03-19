package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import model.entities.Conta;
import model.entities.Extrato;
import model.enums.Operacao;
import model.exception.BancoException;

public class ExtratoServices {

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
                bw.write(ex.getOperacao() + ", " 
                        + ex.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", " 
                        + String.format("%.2f",ex.getValor()) + ", " + String.format("%.2f",ex.getSaldoMomentaneo()));
            }
            //String.format("%.2f", valor)
        } catch (IOException e) {
            throw new BancoException("Erro ao escrever o arquivo extrato: " + e.getMessage());
        } catch (Exception e){
            throw new BancoException("Erro inesperado ao gerar arquivo de extrato: " + e.getMessage());
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
