package practicabustia;

/**
 *
 * @author ava2728
 */
public class Bustia {

    int capacitat = 0;
    int limit = 3;
    int entregant = 0;

    public synchronized void enviar(int escritor) throws InterruptedException {
        while (capacitat == limit) {
            System.out.println("Escriptor " + escritor + ":Esperant,bustia plena");
            wait();
        }
        capacitat++;
            System.out.println("Escriptor " + escritor + ": deixa una carta");
            System.out.println("Capacitat bustia: " + capacitat);
            notify();
    }

    public synchronized void recollir() {
        System.out.println("Carter recull les cartes");
        capacitat = 0;
        notify();
    }
}
