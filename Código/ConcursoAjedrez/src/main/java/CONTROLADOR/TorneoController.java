package CONTROLADOR;

import DAO.GestorBD;
import DAO.JugadorDAO;
import DAO.PartidaDAO;
import DAO.RondaDAO;
import DATA.Jugador;
import DATA.Partida;
import DATA.Ronda;
import GUI.TorneoFrame;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.SwingUtilities;

public class TorneoController {
    private TorneoFrame torneoFrame;

    public TorneoController(TorneoFrame torneoFrame) {
        this.torneoFrame = torneoFrame;
    }

public void simularTorneo() throws SQLException {
    try (Connection conn = GestorBD.conectarBDD()) {
        conn.setAutoCommit(false); // Desactivar auto-commit

        // Limpiar datos anteriores
        GestorBD.ejecutarUpdate("DELETE FROM Partida");
        GestorBD.ejecutarUpdate("DELETE FROM Ronda");
        GestorBD.ejecutarUpdate("DELETE FROM Jugador");
        GestorBD.ejecutarUpdate("ALTER TABLE Jugador AUTO_INCREMENT = 1");

        // Insertar jugadores de prueba
        List<Jugador> jugadores = insertarJugadoresDePrueba();

        // Simular el torneo
        List<Ronda> rondas = new ArrayList<>();
        int numRonda = 1;
        String[] nombresRondas = {"Treintaidósavos", "Dieciseisavos", "Octavos", "Cuartos", "Semifinales", "Final"};
        Random random = new Random(); // Para generar números aleatorios

        while (jugadores.size() > 1) {
            String nombreRonda = nombresRondas[numRonda - 1];
            Ronda ronda = new Ronda(0, 1, nombreRonda, new Date(System.currentTimeMillis()));
            RondaDAO.crearRonda(ronda); // Guardar la ronda en la BDD
            System.out.println("Ronda creada - ID: " + ronda.getRondaId() + ", Nombre: " + nombreRonda);
            rondas.add(ronda);

            // Simular partidas de la ronda actual
            List<Jugador> ganadores = new ArrayList<>();
            int numPartidas = jugadores.size() / 2;

            for (int i = 0; i < numPartidas; i++) {
                Jugador jugador1 = jugadores.get(i * 2);
                Jugador jugador2 = jugadores.get(i * 2 + 1);

                // Simular la partida
                Jugador ganador = simularPartida(jugador1, jugador2);

                // Generar un tiempo aleatorio entre 20 y 55 minutos
                int minutos = 20 + random.nextInt(36); // 20 + (0 a 35) = 20 a 55
                String tiempo = String.format("%02d:00", minutos); // Formato de reloj digital (ej: 25:00)

                // Crear partida con la información necesaria
                Partida partida = new Partida(
                    jugador1, 
                    jugador2, 
                    ganador, 
                    tiempo, // Tiempo aleatorio en formato de reloj digital
                    Timestamp.valueOf(LocalDateTime.now()), 
                    Timestamp.valueOf(LocalDateTime.now().plusMinutes(minutos)), // Fecha de fin basada en el tiempo aleatorio
                    ronda
                );
                PartidaDAO.crearPartida(partida); // Guardar la partida en la BDD

                ganadores.add(ganador);
            }

            jugadores = ganadores; // Actualizar jugadores para la siguiente ronda
            numRonda++;
        }

        

        conn.commit(); // Confirmar TODOS los cambios al final
        System.out.println("Transacción confirmada.");

        // Actualizar la GUI
        List<Partida> partidasActualizadas = PartidaDAO.obtenerTodasLasPartidas();
        torneoFrame.actualizarRondasEnComboBox();
        torneoFrame.actualizarPartidas(partidasActualizadas);

    } catch (SQLException e) {
        // El rollback se maneja automáticamente al salir del bloque try
        System.err.println("Error en la transacción: " + e.getMessage());
        throw e;
    }
}
   


   
  /// En CONTROLADOR/TorneoController.java
private List<Jugador> insertarJugadoresDePrueba() throws SQLException {
    List<Jugador> jugadores = new ArrayList<>();
    String[] nombres = {
        "Alejandro López", "María Fernández", "Juan Martínez", "Laura Gómez", "Carlos Pérez", "Ana Sánchez", 
        "Luis Ramírez", "Marta Torres", "Sofía García", "David Ruiz", "Pablo González", "Lucía Navarro", 
        "Javier Vargas", "Elena Moreno", "Diego Jiménez", "Claudia Herrera", "Andrés Castro", "Natalia Vega", 
        "Manuel Silva", "Isabel Romero", "Álvaro Ortega", "Cristina Delgado", "Francisco Aguilar", "Raquel Prieto", 
        "Antonio Ríos", "Carmen Serrano", "Miguel Domínguez", "Teresa Pacheco", "Fernando Méndez", "Eva Lozano", 
        "Hugo Molina", "Sara León", "Ricardo Peña", "Irene Campos", "Jaime Cruz", "Patricia Bravo", "Adrián Morales", 
        "Julia Fuentes", "Iván Cano", "Paula Cabrera", "Sergio Muñoz", "Rocío Nieto", "Mario Blanco", "Beatriz Ortiz", 
        "Héctor Márquez", "Andrea Cortés", "Daniel Suárez", "Alicia Guzmán", "Enrique Reyes", "Verónica Castillo", 
        "Óscar Ferrer", "Nuria Medina", "Rubén Arias", "Sandra Benítez", "Gonzalo Mármol", "Silvia Esteban", 
        "Eduardo Pascual", "Inés Carrasco", "Ángel Valero", "Gloria Montes", "Ramón Quintana", "Carolina Cruz", 
        "Pedro Salazar", "Victoria Redondo"
    };

    for (String nombre : nombres) {
        Jugador jugador = new Jugador(0, nombre); // ID temporal 0
        JugadorDAO.crearJugador(jugador); // Guardar jugador en la BDD
        jugadores.add(jugador);
    }

    return jugadores;
}
   
private Jugador simularPartida(Jugador jugador1, Jugador jugador2) {
    Random random = new Random();
    return random.nextBoolean() ? jugador1 : jugador2;
}
}