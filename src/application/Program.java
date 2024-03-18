package application;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import UI.TelaInicial;
import model.exception.BancoException;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // Para que os valores sejam aceitos com ponto (.) como separador.

        Scanner sc = new Scanner(System.in);
        TelaInicial telaInicial = new TelaInicial();

        int opcao = 0;

        try{
            while (opcao != 99) { // Enquanto não escolhe opção 99 (SAIR) continuar exibindo MENU para o usuário
                System.out.println(telaInicial.getMenu());
                System.out.print("Informe o número da opçao: ");
                opcao = sc.nextInt();
                executarOpcao(telaInicial, opcao, sc);
            }
        } catch (InputMismatchException e){
            System.out.println("\n\n***** Dado informado não corresponde com o esperado ****** \n\n");
        }


        sc.close();
    }

    private static void executarOpcao(TelaInicial telaInicial, int opcao, Scanner sc) {
        try {
            switch (opcao) {
                case 99:
                    break;
                case 1:
                    telaInicial.opcaoAberturaConta(sc);
                    break;
                case 2:
                    telaInicial.efetuarDeposito(sc);
                    break;
                case 3:
                    telaInicial.saque(sc);
                    break;
                case 4:
                    telaInicial.alterarLimite(sc);
                    break;
                case 5:
                    telaInicial.transferencias(sc);
                    break;
                case 6:
                    telaInicial.gerarArquivoExtrato(sc);
                    break;
                case 7:
                    telaInicial.imprimirExtratoTela(sc);
                    break;
                case 8:
                    telaInicial.imprimirDepositos(sc);
                    break;
                case 9:
                    telaInicial.imprimirSaques(sc);
                    break;
                case 10:
                    telaInicial.aplicarTaxaRemuneracao(sc);
                    break;
                default:
                    System.out.println("\n\n***** Opção inválida. ****** \n\n");
            }

        } catch (BancoException e) {
            clearScreen();
            System.out.println("\n********** Erro: " + e.getMessage() + " **************");
        } catch (Exception e) {
            System.out.println("\n\n ********** Erro inesperado: " + e.getMessage() + "*********** \n\n");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
