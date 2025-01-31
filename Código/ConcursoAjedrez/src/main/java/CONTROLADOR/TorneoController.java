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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TorneoController {
    private TorneoFrame torneoFrame;

    public TorneoController(TorneoFrame torneoFrame) {
        this.torneoFrame = torneoFrame;
    }

public void simularTorneo() throws SQLException {
    try (Connection conn = GestorBD.conectarBDD()) {
        conn.setAutoCommit(false); // Desactivar auto-commit

        GestorBD.ejecutarUpdate("DELETE FROM Partida");
        GestorBD.ejecutarUpdate("DELETE FROM Ronda");
        GestorBD.ejecutarUpdate("DELETE FROM Jugador");
        GestorBD.ejecutarUpdate("ALTER TABLE Jugador AUTO_INCREMENT = 1");

        List<Jugador> jugadores = insertarJugadoresDePrueba();

        List<Ronda> rondas = new ArrayList<>();
        int numRonda = 1;
        String[] nombresRondas = {"Treintaidósavos", "Dieciseisavos", "Octavos", "Cuartos", "Semifinales", "Final"};
        Random random = new Random(); 

        while (jugadores.size() > 1) {
            String nombreRonda = nombresRondas[numRonda - 1];
            Ronda ronda = new Ronda(0, 1, nombreRonda, new Date(System.currentTimeMillis()));
            RondaDAO.crearRonda(ronda); 
            System.out.println("Ronda creada - ID: " + ronda.getRondaId() + ", Nombre: " + nombreRonda);
            rondas.add(ronda);

            List<Jugador> ganadores = new ArrayList<>();
            int numPartidas = jugadores.size() / 2;

            for (int i = 0; i < numPartidas; i++) {
                Jugador jugador1 = jugadores.get(i * 2);
                Jugador jugador2 = jugadores.get(i * 2 + 1);

                Jugador ganador = simularPartida(jugador1, jugador2);

                int minutos = 20 + random.nextInt(36); 
                String tiempo = String.format("%02d:00", minutos);

                Partida partida = new Partida(
                    jugador1, 
                    jugador2, 
                    ganador, 
                    tiempo, 
                    Timestamp.valueOf(LocalDateTime.now()), 
                    Timestamp.valueOf(LocalDateTime.now().plusMinutes(minutos)), 
                    ronda
                );
                PartidaDAO.crearPartida(partida); 

                ganadores.add(ganador);
            }

            jugadores = ganadores; 
            numRonda++;
        }

        

        conn.commit(); 
        System.out.println("Transacción confirmada.");

        List<Partida> partidasActualizadas = PartidaDAO.obtenerTodasLasPartidas();
        torneoFrame.actualizarRondasEnComboBox();
        torneoFrame.actualizarPartidas(partidasActualizadas);

    } catch (SQLException e) {
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