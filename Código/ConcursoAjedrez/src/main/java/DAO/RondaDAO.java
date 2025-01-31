package DAO;

import DATA.Jugador;
import DATA.Ronda;
import DATA.Partida;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RondaDAO {

    
 

    
    public static List<Ronda> obtenerRondas() throws SQLException {
    List<Ronda> rondas = new ArrayList<>();
    String query = "SELECT * FROM Ronda ORDER BY ronda_id DESC"; // Ordenar por ID descendente
    try (Connection conn = GestorBD.conectarBDD();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            Ronda ronda = new Ronda(
                rs.getInt("ronda_id"),
                rs.getInt("torneo_id"),
                rs.getString("nombre_ronda"),
                rs.getDate("fecha")
            );
            rondas.add(ronda);
        }
    }
    return rondas;
}
    
public static List<Partida> obtenerPartidasPorRonda(int rondaId) throws SQLException {
    List<Partida> partidas = new ArrayList<>();
    String query = "SELECT * FROM Partida WHERE ronda_id = ?";
    
    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, rondaId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Asegúrate de que los jugadores y el ganador se recuperen correctamente
                Jugador jugadorBlancas = JugadorDAO.obtenerJugadorPorId(rs.getInt("jugador_blancas_id"));
                Jugador jugadorNegras = JugadorDAO.obtenerJugadorPorId(rs.getInt("jugador_negras_id"));
                Jugador ganador = JugadorDAO.obtenerJugadorPorId(rs.getInt("ganador_id"));
                
                Ronda ronda = RondaDAO.obtenerRondaPorId(rondaId);
                
                Partida partida = new Partida(
                    jugadorBlancas,
                    jugadorNegras,
                    ganador,
                    "30:00", // Tiempo calculado dinámicamente
                    rs.getTimestamp("fecha_hora_inicio"),
                    rs.getTimestamp("fecha_hora_fin"),
                    ronda
                );
                partidas.add(partida);
            }
        }
    }
    return partidas;
}


public static Ronda obtenerRondaPorId(int rondaId) throws SQLException {
    Ronda ronda = null;
    String sql = "SELECT ronda_id, torneo_id, nombre_ronda, fecha FROM Ronda WHERE ronda_id = ?";  // Asegúrate de usar el nombre correcto de la tabla y columna

    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, rondaId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("ronda_id");  
                int torneoId = rs.getInt("torneo_id");  
                String nombreRonda = rs.getString("nombre_ronda");  
                Date fecha = rs.getDate("fecha");  

                
                ronda = new Ronda(id, torneoId, nombreRonda, fecha);
            }
        }
    }
    
    return ronda;  // Devuelve el objeto Ronda o null si no se encuentra
}




   
public static void crearRonda(Ronda ronda) throws SQLException {
    String query = "INSERT INTO Ronda (torneo_id, nombre_ronda, fecha) VALUES (?, ?, ?)";
    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setInt(1, ronda.getTorneoId());
        stmt.setString(2, ronda.getNombreRonda());
        stmt.setDate(3, new java.sql.Date(ronda.getFecha().getTime()));
        stmt.executeUpdate();

        // Obtener el ID generado
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                ronda.setRondaId(idGenerado);
                System.out.println("Ronda creada - ID: " + idGenerado + ", Nombre: " + ronda.getNombreRonda()); // Log
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al crear ronda: " + e.getMessage()); // Log de error
        throw e;
    }
}
}