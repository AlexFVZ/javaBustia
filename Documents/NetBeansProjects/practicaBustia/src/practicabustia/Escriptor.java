package practicabustia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ava2728
 */
public class Escriptor extends Thread{
    int escriptor;
    Bustia bustia;

    public Escriptor(int escriptor,Bustia bustia) {
        this.escriptor = escriptor;
        this.bustia = bustia;
    }
    
    public void run(){
        try {
            bustia.enviar(escriptor);
        } catch (InterruptedException ex) {
            
        }
        
    }
}
