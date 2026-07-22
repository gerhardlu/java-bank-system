public class ContaCorrente extends Conta {

    private double limiteChequeEspecial;

    public ContaCorrente(String titular, int numero, double limiteChequeEspecial) {
        super(titular, numero); // chama o construtor da classe "mãe" (conta)
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            System.out.println("valor de saque inválido");
            return;
        }
        if (valor > getSaldo() + limiteChequeEspecial) {
            throw new SaldoInsuficienteException("Saldo insuficiente, mesmo considerando o limite");
        }
        setSaldo(getSaldo() - valor);
        registrarTransacao("Saque: R$ " + valor + " (conta corrente)");
        System.out.println("Saque de R$ " + valor + " realizado com sucesso (conta corrente)");
    }
}
