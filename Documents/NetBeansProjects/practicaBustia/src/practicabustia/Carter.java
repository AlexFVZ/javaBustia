package practicabustia;

/**
 *
 * @author ava2728
 */
public class Carter extends Thread{
    Bustia bustia;

    public Carter(Bustia bustia) {
        this.bustia = bustia;
    }
    
    
    
    public void run(){
        bustia.recollir();
    }
}
