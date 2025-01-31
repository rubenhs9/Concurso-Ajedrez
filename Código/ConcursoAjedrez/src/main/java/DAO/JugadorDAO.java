/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DATA.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rubén
 */
public class JugadorDAO {
    
    

public static List<Jugador> obtenerTodosLosJugadores() throws SQLException {
    List<Jugador> jugadores = new ArrayList<>();
    String sql = "SELECT jugador_id, nombre, puesto FROM Jugador"; // Incluye 'puesto'
    try (Connection conn = GestorBD.conectarBDD();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Jugador jugador = new Jugador(
                rs.getInt("jugador_id"),
                rs.getString("nombre")
            );
            jugadores.add(jugador);
        }
    }
    return jugadores;
}
    
    public static Jugador obtenerJugadorPorId(int jugadorId) throws SQLException {
    String sql = "SELECT * FROM Jugador WHERE jugador_id = ?";
    try (Connection conn = GestorBD.conectarBDD(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, jugadorId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Suponiendo que Jugador tiene un constructor adecuado
                return new Jugador(rs.getInt("jugador_id"), rs.getString("nombre"));
            }
        }
    }
    return null;
}

    // En DAO/JugadorDAO.java
public static void crearJugador(Jugador jugador) throws SQLException {
    String sql = "INSERT INTO Jugador (nombre) VALUES (?)";
    try (Connection conn = GestorBD.conectarBDD();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, jugador.getNombre());
        stmt.executeUpdate();

        // Obtener el ID generado
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                jugador.setJugadorId(rs.getInt(1)); // Asignar el ID generado
            }
        }
    }
}
     
     
}
