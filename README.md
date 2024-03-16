# Aplicação de Banco

- Aplicação bancária destinada ao usuário funcionário do banco.
- Inicialmente possui uma interface básica em CLI.

# :hammer: Funcionalidades do projeto

- `1`. `Abertura de Contas`: Deverá informar os dados do cliente e escolher entre conta corrente ou conta poupança =).
- `2`. `Depósito`: Caso o cliente tenha dinheiro sobrando, pode deixar na conta e se for poupança ainda irá render uns trocados.
- `3`. `Saque`: Obviamente precisa ter saldo para efetuar saques. Caso a conta seja corrente, é considerado o saldo da conta + o limite da mesma.

## :wrench: Como executar o projeto

Você pode [acessar o código fonte do projeto ](https://github.com/flavioqueiroga/desafio-app-banco/tree/main) ou [baixá-lo](https://github.com/flavioqueiroga/desafio-app-banco/archive/refs/heads/main.zip)

## Diagrama de classes

### Diagrama de Entidades

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
        REMUERACAO
        ABERTURA
    }


    class TipoConta{
        <<enumeration>>
        CORRENTE
        POUPANCA
    }
}
```
