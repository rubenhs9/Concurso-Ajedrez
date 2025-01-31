package DAO;

import DATA.Jugador;
import DATA.Partida;
import DATA.Ronda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    

    public static void crearPartida(Partida partida) throws SQLException {
    String query = "INSERT INTO Partida (ronda_id, jugador_blancas_id, jugador_negras_id, ganador_id, fecha_hora_inicio, fecha_hora_fin) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, partida.getRonda().getRondaId());
        stmt.setInt(2, partida.getJugadorBlancas().getJugadorId());
        stmt.setInt(3, partida.getJugadorNegras().getJugadorId());
        stmt.setInt(4, partida.getGanador() != null ? partida.getGanador().getJugadorId() : 0); // Asume que 0 es un valor por defecto si es null
        stmt.setTimestamp(5, partida.getFechaHoraInicio());
        stmt.setTimestamp(6, partida.getFechaFin());
        
        stmt.executeUpdate();
    }
}

    public static void finalizarPartida(int partidaId, int ganadorId) throws SQLException {
        String query = "UPDATE Partida SET estado = 'FINALIZADA', ganador_id = ?, fecha_hora_fin = NOW() WHERE partida_id = ?";
        try (Connection conn = GestorBD.conectarBDD();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ganadorId);
            stmt.setInt(2, partidaId);
            stmt.executeUpdate();
        }
    }

   public static List<Partida> obtenerPartidasPorRonda(int rondaId) throws SQLException {
       System.out.println("Ejecutando consulta para ronda ID: " + rondaId); // Log
    List<Partida> partidas = new ArrayList<>();
    String query = "SELECT * FROM Partida WHERE ronda_id = ?";

    // Primero, obtener la Ronda asociada al rondaId
    Ronda ronda = RondaDAO.obtenerRondaPorId(rondaId); // Asume que tienes un DAO para Ronda que obtiene una Ronda por su ID.

    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, rondaId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Obtener los datos de la partida
                int jugadorBlancasId = rs.getInt("jugador_blancas_id");
                int jugadorNegrasId = rs.getInt("jugador_negras_id");
                int ganadorId = rs.getInt("ganador_id");

                // Obtener los objetos Jugador usando los IDs
                Jugador jugadorBlancas = JugadorDAO.obtenerJugadorPorId(jugadorBlancasId);
                Jugador jugadorNegras = JugadorDAO.obtenerJugadorPorId(jugadorNegrasId);
                Jugador ganador = JugadorDAO.obtenerJugadorPorId(ganadorId);

                // Obtener las fechas de inicio y fin
                Timestamp fechaInicio = rs.getTimestamp("fecha_hora_inicio");
                Timestamp fechaFin = rs.getTimestamp("fecha_hora_fin");

                // Calcular el tiempo de la partida
                String tiempo = "Partida en curso";
                if (fechaFin != null) {
                    long tiempoMillis = fechaFin.getTime() - fechaInicio.getTime();
                    long minutos = (tiempoMillis / (1000 * 60)) % 60;
                    long segundos = (tiempoMillis / 1000) % 60;
                    tiempo = String.format("%02d:%02d", minutos, segundos);
                }

                // Crear la partida con los objetos Jugador y la ronda
                Partida partida = new Partida(jugadorBlancas, jugadorNegras, ganador, tiempo, fechaInicio, fechaFin, ronda);

                // Agregar la partida a la lista
                partidas.add(partida);
            }
        }
    }
    return partidas;
}




  public static List<Partida> obtenerTodasLasPartidas() throws SQLException {
    List<Partida> partidas = new ArrayList<>();
    String sql = "SELECT p.ronda_id, p.jugador_blancas_id, p.jugador_negras_id, p.fecha_hora_inicio, p.fecha_hora_fin, p.estado, p.ganador_id "
               + "FROM Partida p "
               + "JOIN Jugador j1 ON p.jugador_blancas_id = j1.jugador_id "
               + "JOIN Jugador j2 ON p.jugador_negras_id = j2.jugador_id";

    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int rondaId = rs.getInt("ronda_id");  // Obtener el ronda_id
            Ronda ronda = RondaDAO.obtenerRondaPorId(rondaId);  // Obtener la instancia de Ronda
            
            int jugadorBlancasId = rs.getInt("jugador_blancas_id");
            int jugadorNegrasId = rs.getInt("jugador_negras_id");
            Timestamp fechaInicio = rs.getTimestamp("fecha_hora_inicio");
            Timestamp fechaFin = rs.getTimestamp("fecha_hora_fin");
            int ganadorId = rs.getInt("ganador_id");

            // Obtener los objetos Jugador utilizando sus IDs
            Jugador jugador1 = JugadorDAO.obtenerJugadorPorId(jugadorBlancasId);
            Jugador jugador2 = JugadorDAO.obtenerJugadorPorId(jugadorNegrasId);

            // Obtener el objeto ganador basado en el ID
            Jugador ganador = (ganadorId == jugadorBlancasId) ? jugador1 : jugador2;

            // Calcular el tiempo de la partida (solo si fechaFin no es nula)
            String tiempo = "Partida en curso";
            if (fechaFin != null) {
                long tiempoMillis = fechaFin.getTime() - fechaInicio.getTime();
                long minutos = (tiempoMillis / (1000 * 60)) % 60;
                long segundos = (tiempoMillis / 1000) % 60;
                tiempo = String.format("%02d:%02d", minutos, segundos);
            }

            // Crear el objeto Partida con los objetos Jugador y la ronda
            partidas.add(new Partida(jugador1, jugador2, ganador, tiempo, fechaInicio, fechaFin, ronda));
        }
    }
    return partidas;
}



}