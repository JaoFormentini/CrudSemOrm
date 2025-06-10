package wydem.facimp.sistema.pooJava.projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAO {

    public static void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS celulares (
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255) NOT NULL,
                model VARCHAR(255) NOT NULL,
                year INTEGER NOT NULL,
                value REAL NOT NULL,
                description VARCHAR(255),
                amount INTEGER NOT NULL
            )
            """;
        try (Connection conn = Conexao.getConexao(); Statement exec = conn.createStatement()) {
            exec.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCelular(celular celular){

        String sql = "INSERT INTO celulares(name, model, year, value, description, amount) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement psexec = conn.prepareStatement(sql)){

            psexec.setString(1, celular.getName());
            psexec.setString(2, celular.getModel());
            psexec.setInt(3, celular.getYear());
            psexec.setDouble(4, celular.getValue());
            psexec.setString(5, celular.getDescription());
            psexec.setInt(6, celular.getAmount());
            psexec.executeUpdate();
            System.out.println("✅ Celular adicionado!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<celular> listarCelulares() {
        List<celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try (Connection conn = Conexao.getConexao(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                celular cel = new celular(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("value"),
                        rs.getString("description"),
                        rs.getInt("amount")
                );
                lista.add(cel);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar celulares: " + e.getMessage());
        }
        return lista;
    }

    public void updateCelular(celular celular){
        String sql = """
            UPDATE celulares
            SET name = ?, model = ?, year = ?, value = ?, description = ?, amount = ?
            WHERE id = ?
            """;
        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, celular.getName());
            pstmt.setString(2, celular.getModel());
            pstmt.setInt(3, celular.getYear());
            pstmt.setDouble(4, celular.getValue());
            pstmt.setString(5, celular.getDescription());
            pstmt.setInt(6, celular.getAmount());
            pstmt.setInt(7, celular.getId());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Celular atualizado!");
            } else {
                System.out.println("⚠️ Celular com ID " + celular.getId() + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar celulares: " + e.getMessage());
        }
    }

    public void deletCelular(int id){
        String sql = "DELETE FROM celulares WHERE id = ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("✅ Celular excluido!");
            } else {
                System.out.println("⚠ Celular com id" + id + " nao encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar o celular!" + e.getMessage());
        }
    }



//    public void cadastrarComputador(Computador computador){
//        PreparedStatement ps = null;
//        Boolean verificaMac = false;
//
//        try {
//            Statement statement = Conexao.getConexao().createStatement();
//            String sql = "SELECT mac FROM computador";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                String mac = resultSet.getString("mac");
//                if(mac.contains(computador.getMac())){
//                    verificaMac = true;
//                }
//            }
//
//            if (verificaMac == false) {
//                sql = "INSERT INTO computador (local_host_name, data_de_instalacao, processador, ram_size, sistema_operacional, ip, mac, loja, data_atual) VALUES(?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
//
//                ps = Conexao.getConexao().prepareStatement(sql);
//                ps.setString(1, computador.getLocalHostName());
//                ps.setString(2, computador.getDataDeInstalacao());
//                ps.setString(3, computador.getProcessador());
//                ps.setDouble(4, computador.getMemoriaRam());
//                ps.setString(5, computador.getSistemaOperacional());
//                ps.setString(6, computador.getIp());
//                ps.setString(7, computador.getMac());
//                ps.setString(8, computador.getLoja());
//
//                ps.execute();
//                ps.close();
//            } else {
//                sql = "UPDATE computador SET local_host_name = ?, data_de_instalacao = ?, processador = ?, ram_size = ?, sistema_operacional = ?, ip = ?, loja = ?, data_atual = CURDATE() WHERE mac = ?";
//
//                ps = Conexao.getConexao().prepareStatement(sql);
//                ps.setString(1, computador.getLocalHostName());
//                ps.setString(2, computador.getDataDeInstalacao());
//                ps.setString(3, computador.getProcessador());
//                ps.setDouble(4, computador.getMemoriaRam());
//                ps.setString(5, computador.getSistemaOperacional());
//                ps.setString(6, computador.getIp());
//                ps.setString(7, computador.getLoja());
//                ps.setString(8, computador.getMac());
//
//                ps.executeUpdate();
//                ps.close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }



}
