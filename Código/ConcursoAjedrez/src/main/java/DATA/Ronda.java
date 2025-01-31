package DATA;

import java.sql.Date;
import java.util.List;

public class Ronda {
    private int rondaId;
    private int torneoId;
    private String nombreRonda;
    private Date fecha;
    private List<Partida> partidas;  // Nueva lista de partidas

    public Ronda(int rondaId, int torneoId, String nombreRonda, Date fecha) {
        this.rondaId = rondaId;
        this.torneoId = torneoId;
        this.nombreRonda = nombreRonda;
        this.fecha = fecha;
    }

    public int getRondaId() {
        return rondaId;
    }

    public void setRondaId(int rondaId) {
        this.rondaId = rondaId;
    }

    public int getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(int torneoId) {
        this.torneoId = torneoId;
    }

    public String getNombreRonda() {
        return nombreRonda;
    }

    public void setNombreRonda(String nombreRonda) {
        this.nombreRonda = nombreRonda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public String toString() {
        return nombreRonda ;
    }
}
