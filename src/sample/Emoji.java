package sample;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 *
 * se aplica una  "CLASE" especial para los emojis Y
 * se aplica concepto de Herencia
 * Abstraccion de que es un emoji
 *
 * */
public class Emoji extends Mensaje {
    /**
     * Se aplica concepto de encapsulamiento y atributo.
     */
    private Image emoji;

    public Emoji(String mensaje) {
        super(mensaje);

    }
    public void setEmoji() throws FileNotFoundException {
        FileInputStream input = new FileInputStream ("C:/Users/User/Documents/GitHub/Trabajo_Extraclase1_DatosI_1semastre_2020/src/Emojis/"+this.getMensaje()+".png");
        emoji=new Image(input);

    }

    /**
     *
     * @return Image
     * concepto:"Metodo" para optener el emoji
     */
    public Image getEmoji(){
        return emoji;
    }
}
