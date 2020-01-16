package zad1;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RmiServer {
    public static void main(String[] args) {

        try {
            RmiImpl ref = new RmiImpl("Numery.txt");

            Context initialNamingContext = new InitialContext();
            initialNamingContext.rebind("RmiService", ref );

            System.out.println("Rmi Server: Ready...");
        }
        catch (Exception ex) {
            System.out.println("Trouble: " + ex);
            ex.printStackTrace();
        }
    }
}
