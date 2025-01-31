package DATA;

import java.sql.Timestamp;

public class Partida {
    private int partidaId;
    private Ronda ronda;
    private Jugador jugadorBlancas;
    private Jugador jugadorNegras;
    private Jugador ganador;
    private String tiempo;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaFin;
    
//
//      public Partida(Jugador jugadorBlancas, Jugador jugadorNegras, String ganador, String tiempo, Timestamp fechaInicio, Timestamp fechaFin) {
//        this.jugadorBlancas = jugadorBlancas;
//        this.jugadorNegras = jugadorNegras;
//        this.ganador = ganador;
//        this.tiempo = tiempo;
//        this.fechaHoraInicio = fechaInicio;
//        this.fechaFin = fechaFin;
//    }
      
      
      public Partida(Jugador jugadorBlancas, Jugador jugadorNegras, Jugador ganador, String tiempo, Timestamp fechaInicio, Timestamp fechaFin, Ronda ronda) {
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        this.ganador = ganador;
        this.tiempo = tiempo;
        this.fechaHoraInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ronda = ronda;
    }

    public int getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(int partidaId) {
        this.partidaId = partidaId;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public Jugador getJugadorBlancas() {
        return jugadorBlancas;
    }

    public void setJugadorBlancas(Jugador jugadorBlancas) {
        this.jugadorBlancas = jugadorBlancas;
    }

    public Jugador getJugadorNegras() {
        return jugadorNegras;
    }

    public void setJugadorNegras(Jugador jugadorNegras) {
        this.jugadorNegras = jugadorNegras;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

}