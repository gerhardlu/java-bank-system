public class Conta {

    // atributs: as informações que toda conta precisa ter
    private String titular;
    private int numero;
    private double saldo;

    // construtos: como uma conta "nasce"
    public Conta(String titular,  int numero)  {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0; // toda conta começar com saldo zero
    }
    protected double getSaldo() {
        return this.saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método: depositar dinheiro
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido");
            return;
        }
        this.saldo += valor;
        System.out.println("Depósito de R$ " + valor + " realizado com sucesso");

    }

    // Método: sacar dinheiro
    public void sacar(double valor) throws SaldoInsuficienteException{
        if (valor <= 0) {
            System.out.println("Valor de saque inválido");
            return;
        }
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para sacar R$ " + valor);
        }
        this.saldo -= valor;
        System.out.println("Saque de R$ " + valor + " realizado com sucesso");
    }

    // Método: exibir o saldo atual
    public void verSaldo() {
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numero);
        System.out.println("Saldo atual: " + saldo);
    }


}