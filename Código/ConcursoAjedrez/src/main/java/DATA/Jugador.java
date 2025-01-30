/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import java.util.Date;

/**
 *
 * @author Rubén
 */
public class Jugador {
    private int jugador_id;
    private String nombre;
    private Date fecha_inscripcion;

    public Jugador(int jugador_id, String nombre, Date fecha_inscripcion) {
        this.jugador_id = jugador_id;
        this.nombre = nombre;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    @Override
    public String toString() {
        return "Jugador{" + "jugador_id=" + jugador_id + ", nombre=" + nombre + ", fecha_inscripcion=" + fecha_inscripcion + '}';
    }
    
}
