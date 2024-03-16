package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

import model.entities.Conta;
import model.entities.Extrato;
import model.enums.Operacao;
import model.enums.TipoConta;
import model.services.ContaServices;
import model.services.ExtratoServices;
import model.services.TransacaoService;

public final class TelaInicial {

    private ContaServices contaServices;
    private TransacaoService transacaoServices;
    private ExtratoServices extratoServices;

    public TelaInicial() {
        this.contaServices = new ContaServices();
        this.transacaoServices = new TransacaoService();
        this.extratoServices = new ExtratoServices();
    }

    public String getMenu() {

        StringBuilder texto = new StringBuilder("\n##########  MENU PRINCIPAL ###############\n");
        texto.append("1. Abertura de Conta.\n");
        texto.append("2. Efetuar deposito.\n");
        texto.append("3. Retirada (saque).\n");
        texto.append("4. Alteração de limite.\n");
        texto.append("5. Transferencias.\n");
        texto.append("6. Exportação de histórico de transações (CSV).\n");
        texto.append("7. Imprimir extrato da conta na tela.\n");
        texto.append("8. Imprimir todos Depósitos.\n");
        texto.append("9. Imprimir todos os saques.\n");
        texto.append("10. APlicar taxa de remuneração.\n");
        texto.append("99. SAIR");

        return texto.toString();
    }

    public void opcaoAberturaConta(Scanner sc) {
        imprimeTitulo("Abertura de Conta");
        String nomeCliente = inputString(sc, "Nome do cliente: ");
        long cpf = inputLong(sc, "CPF: ");
        LocalDate dataNascimento = inputDate(sc, "Data nascimento (DD/MM/YYYY): ");
        int numConta = inputInt(sc, "Número da conta: ");
        int numAgencia = inputInt(sc, "Número da agencia: ");
        String tipo = inputString(sc, "Tipo da conta (CC (Para conta corrente) ou CP (para conta poupança.)): ");
        TipoConta tipoConta = TipoConta.getPorCodigo(tipo);

        Conta conta = null;
        if (tipoConta.equals(TipoConta.CORRENTE)) {
            Double limite = inputDouble(sc, "Limite: ");
            conta = contaServices.criarContaCC(tipoConta, numConta, numAgencia, nomeCliente, cpf, dataNascimento,
                    limite);

        } else if (tipoConta.equals(TipoConta.POUPANCA)) {
            Double taxaRemuneracao = inputDouble(sc, "Taxa de Remuneração: ");
            conta = contaServices.criarContaCP(tipoConta, numConta, numAgencia, nomeCliente, cpf, dataNascimento,
                    taxaRemuneracao);
        }

        System.out.println("\n ******** A conta de número: " + conta.getNumero() + " foi criada com sucesso ! ********");

    }

    public void efetuarDeposito(Scanner sc) {

        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe valor do deposito: ");

        transacaoServices.depositar(valor, numeroConta);

        System.out.println("\n********* Deposito realizado com sucesso **********");
    }

    public void saque(Scanner sc) {
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe valor do saque: ");

        transacaoServices.saque(valor, numeroConta);

        System.out.println("\n*************** Saque realizado com sucesso! **********");
    }

    public void alterarLimite(Scanner sc) {
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        double valor = inputDouble(sc, "Informe o novo limite: ");

        contaServices.alterarLimite(numeroConta, valor);

        System.out.println("\n *********  Limite alterado com sucesso ********** ");
    }

    public void transferencias(Scanner sc) {
        clearScreen();
        System.out.println("###########  TRANSFERENCIAS  #########");
        int numeroContaOrigem = inputInt(sc, "Informe a conta de Origem: ");
        int numeroContaDestino = inputInt(sc, "Informe a conta de Destino: ");
        double valor = inputDouble(sc, "Informe o valor da transferencia: ");

        transacaoServices.transferir(numeroContaOrigem, numeroContaDestino, valor);

        System.out.println("\n ******** Transferência realizada com sucesso *********** ");
    }

    public void gerarArquivoExtrato(Scanner sc) {
        int numeroConta = inputInt(sc, "Informe o número da conta: ");
        extratoServices.gerarExtratoCSV(numeroConta);
        System.out.println("\n***** Arquivo salvo com sucesso *********");
    }

    public void imprimirExtratoTela(Scanner sc) {
        clearScreen();
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoImpressao(numeroConta);
        System.out.println("\n###########  EXTRATO DETALHADO  #########");
        extrato.forEach(System.out::println);

    }

    public void imprimirDepositos(Scanner sc) {
        clearScreen();
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoResumido(numeroConta, Operacao.DEPOSITO);

        System.out.println("\n###########  EXTRATO - DEPÓSITOS  #########");
        extrato.forEach(System.out::println);
    }

    public void imprimirSaques(Scanner sc) {
        clearScreen();
        int numeroConta = inputInt(sc, "Informe o nº da conta: ");
        Set<Extrato> extrato = extratoServices.obterExtratoResumido(numeroConta, Operacao.SAQUE);

        System.out.println("###########  EXTRATO - RETIRADAS  #########");
        extrato.forEach(System.out::println);
    }

    public void aplicarTaxaRemuneracao(Scanner sc) {
        clearScreen();
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
