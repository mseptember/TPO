/**
 * @author Wrzesień Maciej S17390
 */

package zad1;


public class Main {

    public static void main(String[] args) {

        try {
            new Thread(() -> new Server()).start();

            Thread.sleep(1000);

            new Thread(() -> new Client()).start();

            new Client("Drugi");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
