public class ContaPoupanca extends Conta {

    private double taxaDeJuros;

    public ContaPoupanca(String titular, int numero, double taxaDeJuros) {
        super(titular, numero);
        this.taxaDeJuros = taxaDeJuros;
    }

    public void renderJuros() {
        double juros = getSaldo() * taxaDeJuros;
        setSaldo(getSaldo() + juros);
        System.out.println("Rendimento de R$ " + juros + " aplicado");
    }
}