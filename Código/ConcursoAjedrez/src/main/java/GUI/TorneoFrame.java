package GUI;

import DAO.PartidaDAO;
import DAO.RondaDAO;
import DATA.Partida;
import DATA.Ronda;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TorneoFrame extends JFrame {
    private JTable tablaPartidas;
    private JComboBox<Ronda> comboRondas;
    private JButton btnMostrarRonda, btnMostrarTodo;
    private JLabel lblEstado;

    public TorneoFrame() {
        setTitle("Resultados del Torneo");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Inicializar la barra de estado primero
        lblEstado = new JLabel("Listo");
        lblEstado.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEstado.setForeground(new Color(100, 100, 100));
        add(lblEstado, BorderLayout.SOUTH);

        // Panel superior con opciones
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSuperior.setBackground(new Color(240, 240, 240));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboRondas = new JComboBox<>();
        comboRondas.setPreferredSize(new Dimension(250, 40)); // Aumentar el tamaño del combo
        comboRondas.setFont(new Font("Arial", Font.PLAIN, 14)); // Aumentar el tamaño de la fuente
        cargarRondas(); // Cargar nombres de las rondas en el combo

        btnMostrarRonda = new JButton("Mostrar por Ronda", new ImageIcon("icons/filter.png"));
        btnMostrarRonda.setBackground(new Color(52, 152, 219));
        btnMostrarRonda.setForeground(Color.WHITE);
        btnMostrarRonda.setFocusPainted(false);
        btnMostrarRonda.setFont(new Font("Arial", Font.BOLD, 14)); // Aumentar el tamaño de la fuente
        btnMostrarRonda.setPreferredSize(new Dimension(200, 40)); // Aumentar el tamaño del botón

        btnMostrarTodo = new JButton("Mostrar Todo el Torneo", new ImageIcon("icons/list.png"));
        btnMostrarTodo.setBackground(new Color(46, 204, 113));
        btnMostrarTodo.setForeground(Color.WHITE);
        btnMostrarTodo.setFocusPainted(false);
        btnMostrarTodo.setFont(new Font("Arial", Font.BOLD, 14)); // Aumentar el tamaño de la fuente
        btnMostrarTodo.setPreferredSize(new Dimension(220, 40)); // Aumentar el tamaño del botón

        panelSuperior.add(new JLabel("Ronda:"));
        panelSuperior.add(comboRondas);
        panelSuperior.add(btnMostrarRonda);
        panelSuperior.add(btnMostrarTodo);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar resultados
        tablaPartidas = new JTable();
        tablaPartidas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPartidas.setRowHeight(25);
        tablaPartidas.setSelectionBackground(new Color(52, 152, 219));
        tablaPartidas.setSelectionForeground(Color.WHITE);
        tablaPartidas.setGridColor(new Color(200, 200, 200));

        // Personalizar el encabezado de la tabla
        JTableHeader header = tablaPartidas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(52, 152, 219));
        header.setForeground(Color.WHITE);

        // Alternar colores de filas
        tablaPartidas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
                }
                return c;
            }
        });

        add(new JScrollPane(tablaPartidas), BorderLayout.CENTER);

        // Eventos de botones
        btnMostrarRonda.addActionListener(e -> mostrarPorRonda());
        btnMostrarTodo.addActionListener(e -> mostrarTodoElTorneo());
    }

    private void cargarRondas() {
        try {
            List<Ronda> rondas = RondaDAO.obtenerRondas();
            for (Ronda ronda : rondas) {
                comboRondas.addItem(ronda); // Agrega el objeto Ronda directamente
            }
            lblEstado.setText("Rondas cargadas correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las rondas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            lblEstado.setText("Error al cargar las rondas.");
        }
    }
    public void actualizarPartidas(List<Partida> partidas) {
        tablaPartidas.setModel(new PartidaTableModel(partidas));
        lblEstado.setText("Datos actualizados correctamente.");
    }

    private void mostrarPorRonda() {
        Ronda rondaSeleccionada = (Ronda) comboRondas.getSelectedItem();
        if (rondaSeleccionada == null) {
            lblEstado.setText("Seleccione una ronda.");
            return;
        }

        try {
            List<Partida> partidas = PartidaDAO.obtenerPartidasPorRonda(rondaSeleccionada.getRondaId());
            tablaPartidas.setModel(new PartidaTableModel(partidas)); // Usar el nuevo modelo
            lblEstado.setText("Mostrando partidas de la ronda: " + rondaSeleccionada.getNombreRonda());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener partidas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            lblEstado.setText("Error al obtener partidas.");
        }
    }
    
    public void actualizarRondasEnComboBox() {
        comboRondas.removeAllItems(); // Limpiar el combo box
        try {
            List<Ronda> rondasActualizadas = RondaDAO.obtenerRondas();
            for (Ronda ronda : rondasActualizadas) {
                comboRondas.addItem(ronda);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rondas: " + e.getMessage());
        }
    }

       private void mostrarTodoElTorneo() {
        try {
            List<Partida> partidas = PartidaDAO.obtenerTodasLasPartidas();
            tablaPartidas.setModel(new PartidaTableModel(partidas)); // Usar el nuevo modelo
            lblEstado.setText("Mostrando todas las partidas del torneo.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener todas las partidas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            lblEstado.setText("Error al obtener todas las partidas.");
        }
    }
}