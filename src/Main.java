public class Main {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente("Gerhard", 111, 500);
        cc.depositar(200);

        try {
            cc.sacar(600);
            cc.sacar(1000); // deve acabar com o limite
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        cc.verSaldo();
    }
}