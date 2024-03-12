package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Conta;
import model.enums.TipoConta;
import model.services.ContaServices;
import model.services.TransacaoService;

public final class TelaInicial {

    private ContaServices contaServices;
    private TransacaoService transacaoServices;

    public TelaInicial() {
        this.contaServices = new ContaServices();
        this.transacaoServices = new TransacaoService();
    }

    public String getMenu() {

        StringBuilder texto = new StringBuilder("MENU PRINCIPAL: \n");
        texto.append("1. Abertura de Conta.\n");
        texto.append("2. Efetuar deposito.\n");
        texto.append("3. Retirada (saque).\n");
        texto.append("4. Alteração de limite.\n");
        texto.append("5. Transferencias.\n");
        texto.append("6. Exportação de histórico de transações (CSV).\n");
        texto.append("7. Imprimir extrato da conta na tela.\n");
        texto.append("8. Imprimir todos Depósitos.\n");
        texto.append("9. Imprimir todos os saques.\n");
        texto.append("0. SAIR");
        
        return texto.toString();
    }

    public void opcaoAberturaConta(Scanner sc) {

        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.next();
        System.out.print("CPF: ");
        long cpf = sc.nextLong();
        System.out.print("Data nascimento (DD/MM/YYYY): ");
        LocalDate dataNascimento = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Número da conta: ");
        int numConta = sc.nextInt();
        System.out.print("Número da agencia: ");
        int numAgencia = sc.nextInt();
        System.out.print("Tipo da conta (CC (Para conta corrente) ou CP (para conta poupança.)): ");
        String tipo = sc.next();
        TipoConta tipoConta = TipoConta.getPorCodigo(tipo);

        Conta conta = null;
        if (tipoConta.equals(TipoConta.CORRENTE)) {
            System.out.print("Limite: ");
            Double limite = sc.nextDouble();
            conta = contaServices.criarContaCC(tipoConta, numConta, numAgencia, nomeCliente, cpf, dataNascimento, limite);

        } else if (tipoConta.equals(TipoConta.POUPANCA)) {
            System.out.print("Taxa de Remuneração: ");
            Double taxaRemuneracao = sc.nextDouble();
            conta = contaServices.criarContaCP(tipoConta, numConta, numAgencia, nomeCliente, cpf, dataNascimento,
                    taxaRemuneracao);
        }

        System.out.println("A conta de número: " + conta.getNumero() + " foi criada com sucesso !");

    }

    public void efetuarDeposito(Scanner sc) {
        
        System.out.print("Informe o número da conta: ");
        int numeroConta = sc.nextInt();
        
        System.out.print("Informe valor do deposito: ");
        double valor = sc.nextDouble();

        transacaoServices.depositar(valor, numeroConta);

    }

    public void saque(Scanner sc) {
        System.out.print("Informe o número da conta: ");
        int numeroConta = sc.nextInt();
        
        System.out.print("Informe valor do saque: ");
        double valor = sc.nextDouble();

        transacaoServices.saque(valor, numeroConta);
    }

    public void alterarLimite(Scanner sc) {
        System.out.print("Informe o número da conta: ");
        int numeroConta = sc.nextInt();
        
        System.out.print("Informe o novo limite: ");
        double valor = sc.nextDouble();

        contaServices.alterarLimite(numeroConta, valor);
    }


   
}
