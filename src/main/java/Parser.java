import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Parser {

    private String getToday = "https://openexchangerates.org/api/latest.json?app_id=d824a51e637e410ca44052ae9bf75a92";

    private String getYesterday = "https://openexchangerates.org/api/historical/.json?app_id=d824a51e637e410ca44052ae9bf75a92";


    public Root parseToday (String currency) throws IOException {
        Root root = new Root();
        URL url = new URL(getToday);
        JSONParser parser = new JSONParser();
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        InputStream in = connect.getInputStream();

        try (InputStreamReader isr = new InputStreamReader(in)) {
            JSONObject jct = (JSONObject) parser.parse(isr);
            JSONObject ratesObj = (JSONObject) jct.get("rates");
            root.setName(currency);
            double value = (double) ratesObj.get(root.getName());

            root.setValue(value);
            return root;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Root parseYestarday (String currency) throws IOException {
        Date today = new Date();
        Instant before = today.toInstant().minus(Duration.ofDays(1));
        Date yesterday = Date.from(before);
        String date = String.format("%tF", yesterday);
        StringBuilder sb = new StringBuilder(getYesterday);
        sb.insert(45, date);
        getYesterday = String.valueOf(sb);

        Root root = new Root();
        URL url = new URL(getYesterday);
        JSONParser parser = new JSONParser();
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        InputStream in = connect.getInputStream();

        try (InputStreamReader isr = new InputStreamReader(in)) {
            JSONObject jct = (JSONObject) parser.parse(isr);
            JSONObject ratesObj = (JSONObject) jct.get("rates");
            root.setName(currency);
            double value = (double) ratesObj.get(root.getName());

            root.setValue(value);
            return root;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
