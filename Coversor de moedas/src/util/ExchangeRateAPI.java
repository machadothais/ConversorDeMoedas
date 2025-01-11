package util;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ExchangeRateAPI {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double getExchangeRate(String from, String to) throws Exception {
        String urlStr = API_URL + from;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Erro ao obter a taxa de câmbio: " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        JSONObject json = new JSONObject(content.toString());
        return json.getJSONObject("rates").getDouble(to);
    }
}
