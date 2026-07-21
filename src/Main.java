public class Main {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente("Gerhard", 111, 500);
        cc.depositar(200);
        cc.sacar(600); // usa o cheque especial (200 + 500 de limite)
        cc.verSaldo();

        ContaPoupanca cp = new ContaPoupanca("Gerhard", 222, 0.01);
        cp.depositar(1000);
        cp.renderJuros();
        cp.verSaldo();
    }
}