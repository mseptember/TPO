package zad1;

import javax.rmi.PortableRemoteObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class RmiImpl extends PortableRemoteObject implements RmiInterface {

    private Map<String, String> pbMap = new HashMap<String, String>();

    public RmiImpl(String fileName) throws RemoteException{
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" +", 2);
                pbMap.put(info[0], info[1]);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }

    public String getImie(String imie) throws RemoteException { //zwraca numer telefonu
        if (!pbMap.containsKey(imie)) return "321 \n" + "Nie istnieje taka osoba";
        return "123 \n" + "OK \n" + (String) pbMap.get(imie);
    }

    public String addImieNumer(String imie, String nr) throws RemoteException { //dopisanie do książki imienia i numeru
        if (pbMap.containsKey(imie)) return "404 \n" + "Już istnieje"; //404-już istnieje
        pbMap.put(imie, nr);
        return "420 \n" + " Dodano " + imie;
    }

    public String replaceImieNumer(String imie, String nr) throws RemoteException { //zamiana numeru
        if (!pbMap.containsKey(imie)) return "321 \n" + "Nie istnieje taka osoba";
        pbMap.replace(imie, nr);
        return "666 \n" +  "Zastąpiono numer " + imie;
    }

    public String bye() throws RemoteException { //koniec komunikacji
        return null;
    }
}
