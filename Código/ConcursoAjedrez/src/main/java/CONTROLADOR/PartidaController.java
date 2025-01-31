
package CONTROLADOR;

import java.sql.Timestamp;
import java.time.Duration;

/**
 *
 * @author Rubén
 */
public class PartidaController {
    public static String calcularTiempo(Timestamp inicio, Timestamp fin) {
        if (fin == null) return "Partida en curso";
        long tiempoMillis = fin.getTime() - inicio.getTime();
        Duration duration = Duration.ofMillis(tiempoMillis);
        long minutos = duration.toMinutes();
        long segundos = duration.minusMinutes(minutos).getSeconds();
        return String.format("%02d:%02d", minutos, segundos);
    }
    
}
