import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContaDAO {

    private String url = "jdbc:h2:./banco_teste";
    private String usuario = "sa";
    private String senha = "";

    // Garante que a tabela existe, sempre que o DAO for usado
    public ContaDAO() {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            Statement comando = conexao.createStatement();
            comando.execute("CREATE TABLE IF NOT EXISTS contas (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "titular VARCHAR(100), " +
                    "numero INT, " +
                    "saldo DOUBLE)");
        } catch (SQLException e) {
            System.out.println("Erro ao preparar tabela: " + e.getMessage());
        }
    }

    // Salva uma conta nova no banco
    public void salvar(Conta conta) {
        String sql = "INSERT INTO contas (titular, numero, saldo) VALUES (?, ?, ?)";
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            PreparedStatement insert = conexao.prepareStatement(sql);
            insert.setString(1, conta.getTitular());
            insert.setInt(2, conta.getNumero());
            insert.setDouble(3, conta.getSaldo());
            insert.executeUpdate();
            System.out.println("Conta salva no banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar conta: " + e.getMessage());
        }
    }

    // Lista todas as contas salvas no banco
    public void listarTodas() {
        String sql = "SELECT * FROM contas";
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(sql);

            System.out.println("--- Contas no banco de dados ---");
            while (resultado.next()) {
                System.out.println(
                        "ID: " + resultado.getInt("id") +
                                " | Titular: " + resultado.getString("titular") +
                                " | Numero: " + resultado.getInt("numero") +
                                " | Saldo: " + resultado.getDouble("saldo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }
    }
}