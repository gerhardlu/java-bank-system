public class Main {
    public static void main(String[] args) {
        ContaDAO dao = new ContaDAO();

        ContaCorrente cc = new ContaCorrente("Gerhard", 333, 500);
        cc.depositar(1200);

        dao.salvar(cc);
        dao.listarTodas();
    }
}