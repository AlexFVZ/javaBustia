package practicabustia;

import static java.lang.Thread.sleep;

/**
 *
 * @author ava2728
 */
public class PracticaBustia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Escriptor[] es;
        Bustia b;
        Carter c;
        int cantEsc = 5;
        es = new Escriptor[cantEsc];
        b = new Bustia();
        c = new Carter(b);
        for (int i = 0; i < es.length; i++) {
            es[i] = new Escriptor(i, b);
        }

        while (true) {
            for (int i = 0; i < es.length; i++) {
                es[i].start();
            }
            sleep(2000);
            c.start();
            
            for (int i = 0; i < es.length; i++) {
                es[i].join();
            }
            c.join();
        }
    }
}
