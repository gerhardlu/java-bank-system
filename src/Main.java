import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();

        ContaCorrente cc = new ContaCorrente("Gerhard", 111, 500);
        ContaPoupanca cp = new ContaPoupanca("Gerhard", 222, 0.01);

        contas.add(cc);
        contas.add(cp);

        try {
            cc.depositar(200);
            cc.sacar(600);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        cp.depositar(1000);
        cp.renderJuros();

        // Percorre todas as contas do banco e mostra o extrato de cada uma
        for (Conta conta : contas) {
            conta.verSaldo();
            conta.verExtrato();
            System.out.println();
        }
    }
}