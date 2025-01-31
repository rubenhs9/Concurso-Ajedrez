package DAO;

import CONTROLADOR.PartidaController;
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



import DATA.Partida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    private static final String QUERY_CREAR_PARTIDA = 
        "INSERT INTO Partida (ronda_id, jugador_blancas_id, jugador_negras_id, ganador_id, fecha_hora_inicio, fecha_hora_fin) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_FINALIZAR_PARTIDA = 
        "UPDATE Partida SET estado = 'FINALIZADA', ganador_id = ?, fecha_hora_fin = ? WHERE partida_id = ?";
    private static final String QUERY_OBTENER_PARTIDAS_POR_RONDA = 
        "SELECT * FROM Partida WHERE ronda_id = ?";
    private static final String QUERY_OBTENER_TODAS_LAS_PARTIDAS = 
    "SELECT p.*, r.nombre_ronda AS ronda_nombre FROM Partida p " +
    "JOIN Ronda r ON p.ronda_id = r.ronda_id";

    public static void crearPartida(Partida partida) throws SQLException {
        try (Connection conn = GestorBD.conectarBDD();
             PreparedStatement stmt = conn.prepareStatement(QUERY_CREAR_PARTIDA)) {
            stmt.setInt(1, partida.getRonda().getRondaId());
            stmt.setInt(2, partida.getJugadorBlancas().getJugadorId());
            stmt.setInt(3, partida.getJugadorNegras().getJugadorId());
            stmt.setInt(4, partida.getGanador() != null ? partida.getGanador().getJugadorId() : 0);
            stmt.setTimestamp(5, partida.getFechaHoraInicio());
            stmt.setTimestamp(6, partida.getFechaFin());
            stmt.executeUpdate();
        }
    }

    public static void finalizarPartida(int partidaId, int ganadorId, Timestamp fechaFin) throws SQLException {
        try (Connection conn = GestorBD.conectarBDD();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FINALIZAR_PARTIDA)) {
            stmt.setInt(1, ganadorId);
            stmt.setTimestamp(2, fechaFin);
            stmt.setInt(3, partidaId);
            stmt.executeUpdate();
        }
    }

    public static List<Partida> obtenerPartidasPorRonda(int rondaId) throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        try (Connection conn = GestorBD.conectarBDD();
             PreparedStatement stmt = conn.prepareStatement(QUERY_OBTENER_PARTIDAS_POR_RONDA)) {
            stmt.setInt(1, rondaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    partidas.add(mapearPartida(rs));
                }
            }
        }
        return partidas;
    }

    public static List<Partida> obtenerTodasLasPartidas() throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        try (Connection conn = GestorBD.conectarBDD();
             PreparedStatement stmt = conn.prepareStatement(QUERY_OBTENER_TODAS_LAS_PARTIDAS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                partidas.add(mapearPartida(rs));
            }
        }
        return partidas;
    }

    private static Partida mapearPartida(ResultSet rs) throws SQLException {
        int rondaId = rs.getInt("ronda_id");
        int jugadorBlancasId = rs.getInt("jugador_blancas_id");
        int jugadorNegrasId = rs.getInt("jugador_negras_id");
        int ganadorId = rs.getInt("ganador_id");
        Timestamp fechaInicio = rs.getTimestamp("fecha_hora_inicio");
        Timestamp fechaFin = rs.getTimestamp("fecha_hora_fin");

        return new Partida(
            JugadorDAO.obtenerJugadorPorId(jugadorBlancasId),
            JugadorDAO.obtenerJugadorPorId(jugadorNegrasId),
            ganadorId > 0 ? JugadorDAO.obtenerJugadorPorId(ganadorId) : null,
            PartidaController.calcularTiempo(fechaInicio, fechaFin),
            fechaInicio,
            fechaFin,
            RondaDAO.obtenerRondaPorId(rondaId)
        );
    }
}
