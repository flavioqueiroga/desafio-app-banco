import java.util.Locale;
import java.util.Scanner;

import UI.TelaInicial;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US); //Para que os valores sejam aceitos com ponto (.) como separador.
        Scanner sc = new Scanner(System.in);

        System.out.println(TelaInicial.getMenu());
        int opcao = sc.nextInt();
    }
}
