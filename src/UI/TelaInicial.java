package UI;

public final class TelaInicial {
 

    public static String getMenu() {
        
        StringBuilder texto = new StringBuilder("Selecione uma das opções: \n");
        texto.append("1. Abertura de Conta.\n");
        texto.append("2. Efetuar deposito.\n");
        texto.append("3. Retirada (saque).\n");
        texto.append("4. Alteração de limite.\n");
        texto.append("5. Transferencias.\n");
        texto.append("6. Exportação de histórico de transações (CSV).\n");
        texto.append("7. Imprimir extrato da conta na tela.\n");
        texto.append("8. Imprimir todos Depósitos./n");
        texto.append("9. Imprimir todos os saques.");
        
        return texto.toString();
    }
   

}
