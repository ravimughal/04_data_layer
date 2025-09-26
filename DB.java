import java.sql.*;

// O que esse código faz ?
// 1. Abrir a porta para o banco de dados
// 2. Mandar instruções (INSERT, UPDATE, DELETE, SELECT)
// 3. Fechar a porta no final, sem a gente precisar lembrar


public class DB {
    private static Connection getConnection() throws SQLException {
        // passamos o endereço do banco de dados, o usuario e a senha do respectivo BD
        return DriverManager.getConnection("jdbc:mysql://localhost/testdb", "user", "pass");
    }

    public static void execute(String sql, Object... params) {
        // Passamos uma frase SQL e os parametros
        // exemplo SQL a ser passado: INSERT INTO clientes (nome) VALUES (?)
        // exemplo de parametros a ser passado: ["Ravi"]
     

        // Aqui abre a porta do banco de dados
        try (Connection conn = getConnection();
            // Pega a frase enviada e cada parametro passado
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
            
            // Envia para o banco
            stmt.executeUpdate(); // executeUpdate() É usado para comando que mudam dados
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String sql, Object... params) {
        try {
            // Conecta ao BD
            Connection conn = getConnection();
            // Pega a frase enviada e cada parametro passado
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
            
            return stmt.executeQuery(); // devolve o ResultSet, executeQuery() É usado para comandos que pedem dados: SELECT
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
