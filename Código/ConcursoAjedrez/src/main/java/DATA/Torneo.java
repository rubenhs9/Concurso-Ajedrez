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
public class Torneo {
    
    private int torneo_id;
    private String nombre;
    private Date fecha_inicio;

    public Torneo(int torneo_id, String nombre, Date fecha_inicio) {
        this.torneo_id = torneo_id;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
    }

    public Torneo(int torneo_id) {
        this.torneo_id = torneo_id;
    }
    
    

    public int getTorneo_id() {
        return torneo_id;
    }

    public void setTorneo_id(int torneo_id) {
        this.torneo_id = torneo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    
    
}
