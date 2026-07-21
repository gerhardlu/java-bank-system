public class ContaCorrente extends Conta{

    private double limiteChequeEspecial;

    public ContaCorrente(String titular, int numero, double limiteChequeEspecial) {
        super(titular, numero); // chama o construtor da classe "mãe" (conta)
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
    if (valor <= 0) {
        System.out.println("valor de saque inválido");
        return;

    }
    if (valor > getSaldo() + limiteChequeEspecial) {
        System.out.println("Saldo insuficiente, mesmo considerando o limite");
        return;
    }
    setSaldo(getSaldo() - valor);
        System.out.println("Saque de R$ " + valor + " realizado com sucesso (Conta Corrente)");
    }
}
