/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Rubén
 */
public class PosicionPieza {
    private int posicion_id;
    private Partida partida_id;
    private String tipo_pieza;
    private Color color;
    private Date fecha_hora_guardado;
    private String formato;

    public PosicionPieza(int posicion_id, Partida partida_id, String tipo_pieza, Color color, Date fecha_hora_guardado, String formato) {
        this.posicion_id = posicion_id;
        this.partida_id = partida_id;
        this.tipo_pieza = tipo_pieza;
        this.color = color;
        this.fecha_hora_guardado = fecha_hora_guardado;
        this.formato = formato;
    }

    public int getPosicion_id() {
        return posicion_id;
    }

    public void setPosicion_id(int posicion_id) {
        this.posicion_id = posicion_id;
    }

    public Partida getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(Partida partida_id) {
        this.partida_id = partida_id;
    }

    public String getTipo_pieza() {
        return tipo_pieza;
    }

    public void setTipo_pieza(String tipo_pieza) {
        this.tipo_pieza = tipo_pieza;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getFecha_hora_guardado() {
        return fecha_hora_guardado;
    }

    public void setFecha_hora_guardado(Date fecha_hora_guardado) {
        this.fecha_hora_guardado = fecha_hora_guardado;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    
    
}
