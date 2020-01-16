package zad1;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class RmiClient {

    public static void main(String[] args) {
        Context ic;
        Object objref;
        RmiInterface ri;

        try {
            ic = new InitialContext();

            objref = ic.lookup("RmiService");
            System.out.println("Client: Obtained a ref. to RMI server.");

            ri = (RmiInterface) PortableRemoteObject.narrow(
                    objref, RmiInterface.class);

            System.out.println(ri.getImie("Miki"));
        }
        catch (Exception ex) {
            System.err.println( "Exception " + ex + "Caught" );
            ex.printStackTrace( );
            return;
        }
    }
}
