# :bank: Aplicação de Banco 
- Aplicação bancária destinada ao usuário funcionário do banco. 
- Inicialmente possui uma interface básica em CLI.

## :hammer: Funcionalidades do projeto

- `1`. `Abertura de Contas`: Deverá informar os dados do cliente e escolher entre conta corrente ou conta poupança.
- `2`. `Depósito`: Caso o cliente tenha dinheiro sobrando, pode deixar na conta e se for poupança ainda irá render uns trocados.
- `3`. `Saque`: Obviamente precisa ter saldo para efetuar saques. Caso a conta seja corrente, é considerado o saldo da conta + o limite da mesma.
- `4`. `Alterar Limite`: Altera o limite da conta. Somente para conta corrente.
- `5`. `Transferêcia`: Efetua transferencias entre contas. Basicamente essa operação executa a funcionalidade de saque na conta de origem e a operação de depósito na conta de destino. Possui limitação de horário. 
- `6`. `Exportação de histórico de transações (CSV)`: Gera arquivo CSV com o histórico de todas as transações (Extrato da Conta). O arquivo é gerado no diretório raiz do projeto. São salvos 4 tipos de oepração no histórico:
  * Abertura
  * Saque
  * Depósito
  * Remuneração
- `7`. `Imprimir extrato da conta na tela`: Imprimi os mesmos dados de histórico de transações na tela. 
- `8`. `Imprimir todos Depósitos.`: Imprime na tela o histórico de todos os depósitos efetuados.
- `9`. `Imprimir todos os saques`: Imprime na tela o histórico de todas as retiradas efetuadas.
- `10`. `Aplicar taxa de remuneração.`: Aplica taxa de remuneração cadastrada na conta e aplica a porcentagem de juros no saldo atual da conta. Somente para conta poupança.
  

## :wrench: Como executar o projeto

- Você pode [acessar o código fonte do projeto ](https://github.com/flavioqueiroga/desafio-app-banco/tree/main) ou [baixá-lo](https://github.com/flavioqueiroga/desafio-app-banco/archive/refs/heads/main.zip)
- Após efetuar o download do projeto, basta executar a classe principal "Program" no pacote "application", que a aplicação irá abrir o MENU inicial no console ou em sua IDE de preferencia.
  

## :books: Utilizando a aplicação
1. Assim que iniciar a execução da aplicação como mostrado no item [Como executar o projeto](#wrench-como-executar-o-projeto)
2. Aparecerá um MENU incial com todas as funcionalidades, cada uma com um respectivo número. Informe o nº da opção para prosseguir.
3. De acordo com a opção será solicitada a entrada de informações para executar as operações.
- **Veja exemplo abaixo. Opção 1 - Abertura de contas**: 
![ExemploUso (6)](https://github.com/flavioqueiroga/desafio-app-banco/assets/43221104/7fe17ada-6878-4533-a95b-c8c537a696f2)

> [!NOTE]
> Toda vez que finalizar uma funcionalidade, será mostrada uma mensagem de sucesso ou erro entre asteriscos.

- Exemplo mensagem de erro de conta não encontrada:
![Exemplo-Erro](https://github.com/flavioqueiroga/desafio-app-banco/assets/43221104/3086c364-755f-4a6f-95da-7d43c8c633a7)

## :white_check_mark: Tecnologias Utilizadas

- ``Java JDK 21``
- ``IntelliJ IDEA``
- ``Paradigma de orientação a objetos e programação funcional ``
- ``Mermaid (confecção de diagramas).``

## :bar_chart: Diagrama de classes

### Pacote Entidades

```mermaid

classDiagram
    Conta <|-- ContaPoupanca
    Conta <|-- ContaCorrente
    Conta --> Cliente
    Conta "1" --> "*" Extrato
    Extrato --> Operacao
namespace Entites {
    class ContaPoupanca{
      -double taxaRemuneracao
      +atualizaTaxaRemuneracao(double novaTaxa) void
      +aplicarRemuneracao() void
    }
    class ContaCorrente{
      -double limite
      +atualizaLimite(double valor) void
      +obterSaldoTransacao() Double
    }
    class Cliente{
      - String nome;
      - Long cpf;
      - LocalDate dataNascimento;
    }
    class Extrato {
      - Operacao operacao;
      - LocalDateTime data;
      - Double valor;
      - Double saldoMomentaneo;
      + toString() String
      + toCompare(Extrato other) int
    }

    class Conta {
      <<Abstract>>
      - int numero
      - int agencia
      - double saldo
      -LocalDate dataAbertura
      +obterSaldoTransacao (double valor) Double
      +saque (double  valor) void
      +deposito (double valor) void
      +atualizaExtrato(Operacao op, LocalDateTime data, Double valor) void
    }

    class Operacao{
        <<enumeration>>
        SAQUE
        DEPOSITO
        REMUNERACAO
        ABERTURA
    }


    class TipoConta{
        <<enumeration>>
        CORRENTE
        POUPANCA
    }
}
```

### Pacote Services
```mermaid
classDiagram
    TransacaoServices -- ContaServices
    ExtratoServices -- ContaServices
    namespace Services {
    
    class ContaServices {
      -List~Conta~ contasCadastradas$ 
      +obterConta(int numeroConta)$ Conta
      +contaExiste(int numeroConta)$ boolean
      +criarContaCC (TipoConta tipo, int numConta, int agencia, Cliente cliente, double remuneracao) Conta
      +criarContaCP (TipoConta tipo, int numConta, int agencia, Cliente cliente, double limite) Conta
      +alterarLimite (int numeroConta, double novoLimite) void
      +aplicarTaxaRemuneracao (int numeroConta) void
    }
    
    class ExtratoServices {
      
      +obterExtratoImpressao(int numeroConta) Set~Extrato~
      +obterExtratoResumido(int numeroConta) Set~Extrato~
      +gerarExtratoCSV(int numeroConta) void
    }

    class TransacaoServices {
      
      -int HORA_LIMITE_DE$ 
      -int HORA_LIMITE_ATE$
      -Double VALOR_LIMITE_HORARIO$
      +depositar(double valor, int numeroConta) void
      +saque(double valor, int numeroConta) void
      +trasnferir (int numContaOrigem, int numContaDestino, double valor) void
      
    }

    

}
```

## :bar_chart: Diagrama de Sequencia

- Abaixo segue diagram de sequencia mostrando o fluxo de chamadas entre as classes na execução de uma funcionalidade. No exemplo o operador escolhe a opção **"Imprimir todos os depósitos".**

```mermaid
   sequenceDiagram
       Actor Operador
       Operador->>Program: Escolhe "Imprimir todos depósitos"
       Program->>+TelaInicial: executarOpcao()
       TelaInicial->>+ExtratoServices: obterExtratoResumido()
       ExtratoServices->>+Conta: obterConta()
       Conta-->>-ExtratoServices: retorna contas cadastradas
       ExtratoServices->>+Conta: getExtrato()
       Conta-->>-ExtratoServices: Lista de depósitos
       ExtratoServices-->>-TelaInicial: Lista de depósitos 
       TelaInicial-->>-Program: imprime os depositos
    

```
