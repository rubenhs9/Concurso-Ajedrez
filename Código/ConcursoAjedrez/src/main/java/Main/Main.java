package Main;

import CONTROLADOR.TorneoController;
import DAO.GestorBD;
import GUI.TorneoFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
         try {
            GestorBD.conectarBDD();
            TorneoFrame frame = new TorneoFrame();
            TorneoController controller = new TorneoController(frame); // Inyectar la GUI
            controller.simularTorneo();

            SwingUtilities.invokeLater(() -> {
                frame.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
