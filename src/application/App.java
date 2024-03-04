package application;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import UI.TelaInicial;
import model.entities.Extrato;
import model.entities.Operacao;
import model.services.ExtratoServices;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US); //Para que os valores sejam aceitos com ponto (.) como separador.
        Scanner sc = new Scanner(System.in);

        System.out.println(TelaInicial.getMenu());
        int opcao = sc.nextInt();

        Set<Extrato> ext = new TreeSet<Extrato>();
        ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2024, 03, 01, 10, 00, 00),100.0, 0.0));
        ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2023, 02, 10, 21, 00, 00),200.0, 100.0));
        ext.add(new Extrato(Operacao.SAQUE, LocalDateTime.of(2024, 02, 28, 21, 00, 00),50.0, 100.0));
        ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2021, 02, 10, 21, 00, 00),1000.0, 100.0));

        ExtratoServices es = new ExtratoServices();
        if (opcao == 8){
            
            Set<Extrato> depositos = ext.stream().filter(e -> e.getOperacao() == Operacao.DEPOSITO).collect(Collectors.toSet());
            
            Double totalDepositos = depositos.stream()
                .map(d -> d.getValor())
                .reduce(0.0, (x,y) -> x + y);


            
            //double total = es.filtroDoExtrato(ext, e -> e.getOperacao().equals(Operacao.DEPOSITO));
            

            depositos.forEach(System.out::println);
            System.out.println("TOTAL: " + totalDepositos);
        }
    }
}
