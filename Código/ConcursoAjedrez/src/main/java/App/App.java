/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import CONTROLADOR.TorneoController;
import DAO.GestorBD;
import GUI.TorneoFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rubén
 */
public class App {
    
    
    
    public void iniciar(){
        try {
            GestorBD.conectarBDD();
            TorneoFrame frame = new TorneoFrame();
            TorneoController controller = new TorneoController(frame); 
            controller.simularTorneo();

            SwingUtilities.invokeLater(() -> {
                frame.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
