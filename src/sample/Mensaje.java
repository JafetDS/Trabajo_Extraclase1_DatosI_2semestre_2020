package sample;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * Clase general para los mensajes
 *Abstraccion de que es un emoji
 * Polimoprfismo pueden haber varios tipos de mensaje.
 * */

public class Mensaje {
    /**
     * Se aplica concepto de encapsulacion.
     */
    private String mensaje;
    private String fecha;
    public Mensaje(String mensaje){
        this.mensaje=mensaje;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.fecha=dtf.format(now);

    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }
}
//yyyy/MM/dd