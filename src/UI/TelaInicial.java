package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import model.entities.Cliente;
import model.entities.Conta;
import model.entities.Extrato;
import model.enums.Operacao;
import model.enums.TipoConta;
import model.services.ContaServices;
import model.services.ExtratoServices;
import model.services.TransacaoServices;

public final class TelaInicial {

    private ContaServices contaServices;
    private TransacaoServices transacaoServices;
    private ExtratoServices extratoServices;

    public TelaInicial() {
        this.contaServices = new ContaServices();
        this.transacaoServices = new TransacaoServices();
        this.extratoServices = new ExtratoServices();
    }

    public String getMenu() {

        String texto = "\n##########  MENU PRINCIPAL ###############\n" + "1. Abertura de Conta.\n" +
                "2. Efetuar deposito.\n" +
                "3. Retirada (saque).\n" +
                "4. Alteração de limite.\n" +
                "5. Transferencias.\n" +
                "6. Exportação de histórico de transações (CSV).\n" +
                "7. Imprimir extrato da conta na tela.\n" +
                "8. Imprimir todos Depósitos.\n" +
                "9. Imprimir todos os saques.\n" +
                "10. Aplicar taxa de remuneração.\n" +
                "99. SAIR";

        return texto;
    }

    public void opcaoAberturaConta(Scanner sc) {
        clearScreen();
        imprimeTitulo("ABERTURA DE CONTA");
        String nomeCliente = inputString(sc, "Nome do cliente: ");
        long cpf = inputLong(sc, "CPF: ");
        LocalDate dataNascimento = inputDate(sc, "Data nascimento (DD/MM/YYYY): ");
        int numConta = inputInt(sc, "Número da conta: ");
        int agencia = inputInt(sc, "Número da agencia: ");
        String tipo = inputString(sc, "Tipo da conta (CC (Para conta corrente) ou CP (para conta poupança.)): ");
        TipoConta tipoConta = TipoConta.getPorCodigo(tipo);

        Cliente cliente = new Cliente(nomeCliente, cpf, dataNascimento);
        Conta conta = null;
        if (tipoConta.equals(TipoConta.CORRENTE)) {
            Double limite = inputDouble(sc, "Limite: ");
            conta = contaServices.criarContaCC(tipoConta, numConta, agencia, cliente, limite);

        } else if (tipoConta.equals(TipoConta.POUPANCA)) {
            Double remuneracao = inputDouble(sc, "Taxa de Remuneração: ");
            conta = contaServices.criarContaCP(tipoConta, numConta, agencia, cliente, remuneracao);
        }
        clearScreen();
        System.out.println("\n ******** A conta de número: " + conta.getNumero() + " foi criada com sucesso ! ********");

    }

    public void efetuarDeposito(Scanner sc) {
        clearScreen();
        imprimeTitulo("DEPÓSITO");
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe valor do deposito: ");

        transacaoServices.depositar(valor, numeroConta);

        System.out.println("\n********* Deposito realizado com sucesso **********");
    }

    public void saque(Scanner sc) {
        clearScreen();
        imprimeTitulo("SAQUE");
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe valor do saque: ");

        transacaoServices.saque(valor, numeroConta);

        System.out.println("\n*************** Saque realizado com sucesso! **********");
    }

    public void alterarLimite(Scanner sc) {
        clearScreen();
        imprimeTitulo("ALTERAR LIMITE");
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe o novo limite: ");

        contaServices.alterarLimite(numeroConta, valor);

        System.out.println("\n *********  Limite alterado com sucesso ********** ");
    }

    public void transferencias(Scanner sc) {
        clearScreen();
        imprimeTitulo("TRANSFERENCIAS");
        int numeroContaOrigem = inputInt(sc, "Informe a conta de Origem: ");
        int numeroContaDestino = inputInt(sc, "Informe a conta de Destino: ");
        double valor = inputDouble(sc, "Informe o valor da transferencia: ");

        transacaoServices.transferir(numeroContaOrigem, numeroContaDestino, valor);

        System.out.println("\n ******** Transferência realizada com sucesso *********** ");
    }

    public void gerarArquivoExtrato(Scanner sc) {
        clearScreen();
        imprimeTitulo(" GERAR ARQUIVO EXTRATO ");
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        extratoServices.gerarExtratoCSV(numeroConta);
        System.out.println("\n***** Arquivo salvo com sucesso *********");
    }

    public void imprimirExtratoTela(Scanner sc) {
        clearScreen();
        imprimeTitulo(" EXTRATO TELA ");
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoImpressao(numeroConta);
        System.out.println("\n###########  EXTRATO DETALHADO  #########");
        extrato.forEach(System.out::println);

    }

    public void imprimirDepositos(Scanner sc) {
        clearScreen();
        imprimeTitulo(" EXTRATO TELA - DEPÓSITOS ");
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoResumido(numeroConta, Operacao.DEPOSITO);

        System.out.println("\n###########  EXTRATO - DEPÓSITOS  #########");
        extrato.forEach(System.out::println);
    }

    public void imprimirSaques(Scanner sc) {
        clearScreen();
        imprimeTitulo(" EXTRATO TELA - SAQUES ");
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoResumido(numeroConta, Operacao.SAQUE);

        System.out.println("###########  EXTRATO - RETIRADAS  #########");
        extrato.forEach(System.out::println);
    }

    public void aplicarTaxaRemuneracao(Scanner sc) {
        clearScreen();
        imprimeTitulo(" APLICAR TAXA DE REMUNERAÇÃO ");
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        contaServices.aplicarTaxaRemuneracao(numeroConta);

        System.out.println("****** Remuneração aplicada com sucesso *********");
    }

    private String inputString(Scanner sc, String label) {
        System.out.print(label);
        return sc.next();

    }

    private Double inputDouble(Scanner sc, String label) {
        System.out.print(label);
        return sc.nextDouble();

    }

    private int inputInt(Scanner sc, String label) {
        System.out.print(label);
        return sc.nextInt();
    }

    private LocalDate inputDate(Scanner sc, String label) {
        System.out.print(label);
        return LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private Long inputLong(Scanner sc, String label) {
        System.out.print(label);
        return sc.nextLong();
    }

    private void imprimeTitulo(String msg) {
        System.out.println("\n\n######### " + msg + " #############");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
