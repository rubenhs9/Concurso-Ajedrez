package DATA;

import DAO.JugadorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int jugadorId;
    private String nombre;
   

     public Jugador(int id, String nombre) {
        this.jugadorId = id;
        this.nombre = nombre;
        
    }

 
     
     
    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Jugador{" + "jugadorId=" + jugadorId + ", nombre=" + nombre + '}';
    }
    
   
    
}