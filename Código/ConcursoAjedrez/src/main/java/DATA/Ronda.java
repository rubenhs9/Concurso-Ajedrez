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
public class Ronda {
    private int ronda_id;
    private Torneo torneo_id;
    private String nombre_ronda;
    private Date fecha;

    public Ronda(int ronda_id, Torneo torneo_id, String nombre_ronda, Date fecha) {
        this.ronda_id = ronda_id;
        this.torneo_id = torneo_id;
        this.nombre_ronda = nombre_ronda;
        this.fecha = fecha;
    }

    public int getRonda_id() {
        return ronda_id;
    }

    public void setRonda_id(int ronda_id) {
        this.ronda_id = ronda_id;
    }

    public Torneo getTorneo_id() {
        return torneo_id;
    }

    public void setTorneo_id(Torneo torneo_id) {
        this.torneo_id = torneo_id;
    }

    public String getNombre_ronda() {
        return nombre_ronda;
    }

    public void setNombre_ronda(String nombre_ronda) {
        this.nombre_ronda = nombre_ronda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
