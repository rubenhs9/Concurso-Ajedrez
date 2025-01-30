/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Rubén
 */
public class Partida {
    private int partida_id;
    private Ronda ronda_id;
    private Jugador jugador_blancas_id;
    private Jugador jugador_negras_id;
    private Jugador ganador_id;
    private Timer duracion;
    private ESTADO_PARTIDA estado;
    private Date fecha_hora_inicio; 
    private Date fecha_hora_fin;

    public Partida(int partida_id, Ronda ronda_id, Jugador jugador_blancas_id, Jugador jugador_negras_id, Jugador ganador_id, Timer duracion, ESTADO_PARTIDA estado, Date fecha_hora_inicio, Date fecha_hora_fin) {
        this.partida_id = partida_id;
        this.ronda_id = ronda_id;
        this.jugador_blancas_id = jugador_blancas_id;
        this.jugador_negras_id = jugador_negras_id;
        this.ganador_id = ganador_id;
        this.duracion = duracion;
        this.estado = estado;
        this.fecha_hora_inicio = fecha_hora_inicio;
        this.fecha_hora_fin = fecha_hora_fin;
    }

    public int getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
    }

    public Ronda getRonda_id() {
        return ronda_id;
    }

    public void setRonda_id(Ronda ronda_id) {
        this.ronda_id = ronda_id;
    }

    public Jugador getJugador_blancas_id() {
        return jugador_blancas_id;
    }

    public void setJugador_blancas_id(Jugador jugador_blancas_id) {
        this.jugador_blancas_id = jugador_blancas_id;
    }

    public Jugador getJugador_negras_id() {
        return jugador_negras_id;
    }

    public void setJugador_negras_id(Jugador jugador_negras_id) {
        this.jugador_negras_id = jugador_negras_id;
    }

    public Jugador getGanador_id() {
        return ganador_id;
    }

    public void setGanador_id(Jugador ganador_id) {
        this.ganador_id = ganador_id;
    }

    public Timer getDuracion() {
        return duracion;
    }

    public void setDuracion(Timer duracion) {
        this.duracion = duracion;
    }

    public ESTADO_PARTIDA getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_PARTIDA estado) {
        this.estado = estado;
    }

    public Date getFecha_hora_inicio() {
        return fecha_hora_inicio;
    }

    public void setFecha_hora_inicio(Date fecha_hora_inicio) {
        this.fecha_hora_inicio = fecha_hora_inicio;
    }

    public Date getFecha_hora_fin() {
        return fecha_hora_fin;
    }

    public void setFecha_hora_fin(Date fecha_hora_fin) {
        this.fecha_hora_fin = fecha_hora_fin;
    }

    @Override
    public String toString() {
        return "Partida{" + "partida_id=" + partida_id + ", ronda_id=" + ronda_id + ", jugador_blancas_id=" + jugador_blancas_id + ", jugador_negras_id=" + jugador_negras_id + ", ganador_id=" + ganador_id + ", duracion=" + duracion + ", estado=" + estado + ", fecha_hora_inicio=" + fecha_hora_inicio + ", fecha_hora_fin=" + fecha_hora_fin + '}';
    }
    
}
