package GUI;

import DATA.Jugador;
import DATA.Partida;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class PartidaTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Jugador Blancas", "Jugador Negras", "Ganador", "Tiempo", "Inicio", "Fin"};
    private final List<Partida> partidas;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public PartidaTableModel(List<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public int getRowCount() {
        return partidas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
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