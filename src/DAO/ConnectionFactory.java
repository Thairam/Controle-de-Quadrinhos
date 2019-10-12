package DAO;

import java.sql.*;
import java.util.TimeZone;

/**
 *
 * @author Thairam Michel
 */
public abstract class ConnectionFactory {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/controledequadrinhos?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1234";

    /**
     * Método para gerar uma conexão
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para fechar uma conexão
     *
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    /**
     * Método para fechar uma conexão e uma statement
     *
     * @param con
     * @param stmt
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        closeConnection(con);
        if (stmt != null) {
            stmt.close();
        }
    }

    /**
     * Método para fechar uma conexão, um statement e um resultSet
     *
     * @param con
     * @param stmt
     * @param rs
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException {
        closeConnection(con, stmt);
        if (rs != null) {
            rs.close();
        }
    }

}
