import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteConexao {
    public static void main(String[] args) {
        String url = "jdbc:h2:./banco_teste";

        try (Connection conexao = DriverManager.getConnection(url, "sa", "")) {
            System.out.println("Conexão com o banco realizada com sucesso!");

            Statement comando = conexao.createStatement();
            comando.execute("CREATE TABLE IF NOT EXISTS contas (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "titular VARCHAR(100), " +
                    "numero INT, " +
                    "saldo DOUBLE)");

            comando.execute("DELETE FROM contas");
            System.out.println("Tabela limpa!");

            String sqlInsert = "INSERT INTO contas (titular, numero, saldo) VALUES (?, ?, ?)";
            PreparedStatement insert = conexao.prepareStatement(sqlInsert);
            insert.setString(1, "Gerhard");
            insert.setInt(2, 111);
            insert.setDouble(3, 500.0);
            insert.executeUpdate();
            System.out.println("Conta inserida com sucesso!");

            ResultSet resultado = comando.executeQuery("SELECT * FROM contas");
            System.out.println("--- Contas cadastradas ---");
            while (resultado.next()) {
                System.out.println(
                        "ID: " + resultado.getInt("id") +
                                " | Titular: " + resultado.getString("titular") +
                                " | Numero: " + resultado.getInt("numero") +
                                " | Saldo: " + resultado.getDouble("saldo")
                );
            }
            String sqlUpdate = "UPDATE contas SET saldo = ? WHERE numero = ?";
            PreparedStatement update = conexao.prepareStatement(sqlUpdate);
            update.setDouble(1, 750.0);
            update.setInt(2, 111);
            update.executeUpdate();
            System.out.println("Saldo atualizado!");

            ResultSet resultadoAtualizado = comando.executeQuery("SELECT * FROM contas");
            System.out.println("--- Contas após atualização ---");
            while (resultadoAtualizado.next()) {
                System.out.println(
                        "ID: " + resultadoAtualizado.getInt("id") +
                                " | Titular: " + resultadoAtualizado.getString("titular") +
                                " | Numero: " + resultadoAtualizado.getInt("numero") +
                                " | Saldo: " + resultadoAtualizado.getDouble("saldo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
