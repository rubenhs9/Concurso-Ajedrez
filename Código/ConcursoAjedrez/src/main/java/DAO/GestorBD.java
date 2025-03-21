package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rubén
 */
public class GestorBD {
    private static final String URL = "jdbc:mysql://localhost:3306/torneo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "ruben1234";
    
    public static Connection conectarBDD() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
     public static void ejecutarUpdate(String sql) throws SQLException {
        try (Connection conn = conectarBDD();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    
    public static void cerrarConexion(Connection conn){
        if (conn != null) {
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
