public class Main {
    public static void main(String[] args) {
        Conta contaDoGerhard = new Conta("Gerhard", 12345);

        contaDoGerhard.depositar(500);
        contaDoGerhard.sacar(150);
        contaDoGerhard.sacar(1000); // deve falhar, saldo insuficiente

        contaDoGerhard.verSaldo();
    }
}