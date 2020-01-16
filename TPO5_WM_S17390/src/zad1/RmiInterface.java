package zad1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {
    public String getImie (String imie) throws RemoteException;

    public String addImieNumer (String imie, String nr) throws RemoteException;

    public String replaceImieNumer (String imie, String nr) throws RemoteException;

    public String bye () throws RemoteException;
}
