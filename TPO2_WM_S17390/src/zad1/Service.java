/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {

    private String kraj;
    private String walutaKraju;
    private String walutaDomyslna;

    private final static String pogoda = "https://api.openweathermap.org";
    private final static String pogodaID = "1f49695d4a9aed2b6c93a8ba1552e935";
    private final static String exchangeRate = "https://exchangeratesapi.io";
    private final static String rateNBP_a = "http://www.nbp.pl/kursy/kursya.html";
    private final static String rateNBP_b = "http://www.nbp.pl/kursy/kursyb.html";

    public Service(String kraj) {
        this.kraj = kraj;
        this.walutaDomyslna = "PLN";
    }

    public String getWeather(String miasto) {
        String url = pogoda + "/data/2.5/weather?q=" + miasto + "&units=metric" + "&APPID=" + pogodaID;
        return getResponse(url);
    }

    public Double getRateFor(String kod_waluty) {
        walutaDomyslna = kod_waluty;

        String url = exchangeRate + "/latest?base=" + walutaKraju;
        String response = getResponse(url);

        return 0.0; //TODO
    }

    public Double getNBPRate() {
        if (walutaKraju.equals("PLN")) {
            return 1.0;
        }

        double kurs = 0.0;
        String url = rateNBP_a;
        String response = getResponse(url);

        if(response.isEmpty()) {
            url = rateNBP_b;
            response = getResponse(url);
        }

        return kurs;
    }

    private String getResponse (String sUrl) {
        StringBuffer sb = new StringBuffer();

        if(sUrl.isEmpty() || sUrl == null) {
            return null;
        }

        try {
            URL url = new URL(sUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            int dane;

            while((dane = inputStream.read()) != -1) {
                sb.append((char) dane);
            }

            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
}
