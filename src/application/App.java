package application;

import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import UI.TelaInicial;
import model.exception.BancoException;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // Para que os valores sejam aceitos com ponto (.) como separador.
        Scanner sc = new Scanner(System.in);
        TelaInicial telaInicial = new TelaInicial();
        
        int opcao = 0;
        do {
            System.out.println(telaInicial.getMenu());
            System.out.print("Informe o número da opçao: ");
            opcao = sc.nextInt();
            executarOpcao(telaInicial, opcao, sc);
        } while (opcao != 0);
        
        

        
    }

    private static void executarOpcao(TelaInicial telaInicial, int opcao, Scanner sc) {
        try {
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    telaInicial.opcaoAberturaConta(sc);
                    break;
                case 2:
                    telaInicial.efetuarDeposito(sc);
                    break;
                case 3:
                    telaInicial.saque(sc);
            }

        } catch (BancoException e) {
            System.out.println("\nErro: " + e.getMessage());
            sc.next();
            
        } catch (DateTimeParseException e) {
            System.out.println("Informe o formato de data correta.");
        }
    }

    public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
    

    // catch (RuntimeException e) {
    // System.out.println("Erro inesperado");
    // 1}

    /*
     * Set<Extrato> ext = new TreeSet<Extrato>();
     * ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2024, 03, 01, 10, 00,
     * 00),100.0, 0.0));
     * ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2023, 02, 10, 21, 00,
     * 00),200.0, 100.0));
     * ext.add(new Extrato(Operacao.SAQUE, LocalDateTime.of(2024, 02, 28, 21, 00,
     * 00),50.0, 100.0));
     * ext.add(new Extrato(Operacao.DEPOSITO, LocalDateTime.of(2021, 02, 10, 21, 00,
     * 00),1000.0, 100.0));
     * 
     * ExtratoServices es = new ExtratoServices();
     * if (opcao == 8){
     * 
     * Set<Extrato> depositos = ext.stream().filter(e -> e.getOperacao() ==
     * Operacao.DEPOSITO).collect(Collectors.toSet());
     * 
     * Double totalDepositos = depositos.stream()
     * .map(d -> d.getValor())
     * .reduce(0.0, (x,y) -> x + y);
     * 
     * 
     * 
     * //double total = es.filtroDoExtrato(ext, e ->
     * e.getOperacao().equals(Operacao.DEPOSITO));
     * 
     * 
     * depositos.forEach(System.out::println);
     * System.out.println("TOTAL: " + totalDepositos);
     * }
     */
}
