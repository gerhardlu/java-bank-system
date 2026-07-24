import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String titular;
    private int numero;
    private double saldo;
    private List<String> transacoes = new ArrayList<>();

    protected void registrarTransacao(String descrisao) {
        transacoes.add(descrisao);
    }

    public Conta(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0;
    }

    public String getTitular() {
        return this.titular;
    }

    public int getNumero() {
        return this.numero;
    }
    protected double getSaldo() {
        return this.saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido");
            return;
        }
        this.saldo += valor;
        transacoes.add("Depósito: R$ " + valor);
        System.out.println("Depósito de R$ " + valor + " realizado com sucesso");
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido");
            return;
        }
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para sacar R$ " + valor);
        }
        this.saldo -= valor;
        transacoes.add("Saque: R$ " + valor);
        System.out.println("Saque de R$ " + valor + " realizado com sucesso");
    }

    public void verSaldo() {
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numero);
        System.out.println("Saldo atual: R$ " + saldo);
    }

    // mostrar o extrato
    public void verExtrato() {
        System.out.println("--- Extrato da conta " + numero + " ---");
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação realizada ainda.");
            return;
        }
        for (String transacao : transacoes) {
            System.out.println(transacao);
        }
    }
}