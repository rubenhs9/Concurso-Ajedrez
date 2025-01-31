package GUI;

import DATA.Partida;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablaPartida extends AbstractTableModel {
    private final String[] columnas = {"Jugador Blancas", "Jugador Negras", "Ganador", "Tiempo", "Inicio", "Fin"};
    private final List<Partida> partidas;

    public TablaPartida(List<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public int getRowCount() {
        return partidas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Partida partida = partidas.get(rowIndex);
        switch (columnIndex) {
            case 0: return partida.getJugadorBlancas().getNombre();
            case 1: return partida.getJugadorNegras().getNombre();
            case 2: return partida.getGanador() != null ? partida.getGanador().getNombre() : "En curso";
            case 3: return partida.getTiempo();
            case 4: return partida.getFechaHoraInicio();
            case 5: return partida.getFechaFin();
            default: return null;
        }
    }
}