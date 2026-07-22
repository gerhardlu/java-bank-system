# Changelog

Histórico de evolução do projeto Java Bank System, documentando o que foi
construído em cada fase de aprendizado e por quê.

## Fundamentos e Encapsulamento

- **feat:** criada a classe `Conta`, com os atributos `titular`, `numero` e
  `saldo`. Essa é a classe base do sistema — o "molde" que representa
  qualquer conta bancária.
- **feat:** adicionados os métodos `depositar()` e `sacar()`, com validações
  para impedir valores negativos ou saques maiores que o saldo disponível.
- **feat:** os atributos da conta são `private` (encapsulamento) — ninguém
  pode alterar o saldo diretamente de fora da classe, só através dos
  métodos que validam a operação.
- **fix:** corrigido o construtor, que estava atribuindo valores fixos
  (ex: sempre "Gerhard" e "12345") em vez de usar os parâmetros recebidos.
  Isso fazia toda conta nova nascer com os mesmos dados, independente do
  que fosse passado.
- **fix:** corrigida a validação do método `depositar()`, que estava com a
  condição invertida e bloqueava todo depósito positivo em vez de
  bloquear valores inválidos.
- **chore:** adicionado o arquivo `.gitignore`, configurado para ignorar
  a pasta `.idea/` e arquivos `.iml` — configurações internas do IntelliJ
  que não fazem sentido serem compartilhadas no repositório.
- **chore:** removido o arquivo `Java.iml`, que já havia sido commitado
  antes do `.gitignore` estar configurado corretamente.

## Herança

- **feat:** criada a classe `ContaCorrente`, que estende `Conta` e adiciona
  a regra de limite de cheque especial — permite que o saldo fique
  negativo até um valor limite definido na criação da conta.
- **feat:** criada a classe `ContaPoupanca`, que estende `Conta` e adiciona
  o método `renderJuros()`, aplicando uma taxa de juros sobre o saldo
  atual.
- **refactor:** adicionados os métodos `getSaldo()` e `setSaldo()` na
  classe `Conta`, com visibilidade `protected`. Isso foi necessário porque
  o atributo `saldo` é `private`, e as subclasses (`ContaCorrente` e
  `ContaPoupanca`) precisam de uma forma controlada de ler e alterar esse
  valor.
- **fix:** corrigida a declaração da classe `ContaCorrente`, que estava
  faltando `extends Conta` — sem isso, a herança não existia de verdade,
  e o construtor não conseguia chamar `super(titular, numero)` porque
  esse construtor pertence à classe `Conta`.

## Tratamento de Exceções

- **feat:** criada a exceção customizada `SaldoInsuficienteException`
  (estende `Exception`), usada para sinalizar formalmente quando uma
  operação de saque não pode ser concluída por falta de saldo.
- **refactor:** o método `sacar()` (em `Conta` e em `ContaCorrente`) foi
  alterado para usar `throw new SaldoInsuficienteException(...)` em vez
  de apenas imprimir uma mensagem de erro no console e sair do método com
  `return`. Isso permite que o erro seja tratado de forma programática por
  quem estiver usando a classe.
- **feat:** adicionado bloco `try/catch` na `Main`, capturando a
  `SaldoInsuficienteException` quando ela é lançada, exibindo a mensagem
  de erro e permitindo que o programa continue rodando normalmente depois
  do erro (em vez de travar).

## Coleções e Extrato

- **feat:** adicionada uma lista (`List<String> transacoes`) dentro da
  classe `Conta`, que guarda um registro em texto de cada depósito e
  saque realizado.
- **feat:** adicionado o método `verExtrato()`, que percorre a lista de
  transações e imprime o histórico completo da conta.
- **feat:** adicionado o método `registrarTransacao()` (protected), usado
  pelas subclasses para adicionar registros na lista de transações da
  conta "mãe".
- **feat:** adicionada uma `List<Conta>` na `Main`, capaz de guardar
  `ContaCorrente` e `ContaPoupanca` juntas na mesma lista — isso funciona
  porque ambas são subtipos de `Conta` (herança). Um laço `for` percorre
  essa lista chamando `verSaldo()` e `verExtrato()` de cada conta.
- **fix:** o método `renderJuros()` em `ContaPoupanca` não estava
  registrando a transação de juros no extrato — corrigido para chamar
  `registrarTransacao()` também.

## Documentação

- **docs:** adicionado o `README.md`, explicando o objetivo do projeto,
  as funcionalidades implementadas e os conceitos de POO praticados.
- **docs:** adicionado este `CHANGELOG.md`, detalhando a evolução do
  projeto fase a fase.