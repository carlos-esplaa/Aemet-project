import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeatherEvents {

    private static String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3Muc2FudGFuYS5lc3BsYTI2QGdtYWlsLmNvbSIsImp0aSI6ImY4YmU0ZjhjLWIyMzItNGRhNC04Nzc1LWMzMjgwM2VmNTY2OSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjczMjYyNTQwLCJ1c2VySWQiOiJmOGJlNGY4Yy1iMjMyLTRkYTQtODc3NS1jMzI4MDNlZjU2NjkiLCJyb2xlIjoiIn0._SWdpKDj0xQDQYjCrRn5k88M8rsK7JfpLDR_UyWQfac";

    public static String getData() {

        String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
        String dataUrl;
        try {
            String response = Jsoup.connect(url)
                    .followRedirects(false)
                    .ignoreContentType(true)
                    .header("accept", "application/json")
                    .header("api_key", apiKey)
                    .method(Connection.Method.GET)
                    .maxBodySize(0).execute().body();
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            dataUrl = jsonObject.get("datos").getAsString();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        return dataUrl;
    }

    public static String getContent(String dataUrl) throws IOException {
        String content = Jsoup.connect(dataUrl)
                .followRedirects(false)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
        JsonArray jsonElements = new Gson().fromJson(content, JsonArray.class);
        return content;
    }

    private static List<WeatherEvents> getWeatherEvents(JsonArray jsonElements) {
        List<WeatherEvents> weatherEvents = new ArrayList<>();
        for (JsonElement jsonElement : jsonElements) {
            Event event = new Event();
            if (jsonElement.getAsJsonObject().get("lon").getAsDouble() > -16 && jsonElement.getAsJsonObject().get("lon").getAsDouble() < -15) {
                if (jsonElement.getAsJsonObject().get("lat").getAsDouble() > 27.5 && jsonElement.getAsJsonObject().get("lat").getAsDouble() < -28.4) {
                    event.setMoment(jsonElement.getAsJsonObject().get("fint").getAsString());
                    event.setStation(jsonElement.getAsJsonObject().get("idema").getAsString());
                    event.setLocation(jsonElement.getAsJsonObject().get("ubi").getAsString());
                    weatherEvents.add(event);

                    try {
                        event.setDegree(jsonElement.getAsJsonObject().get("at").getAsDouble());
                        weatherEvents.add(event);
                    } catch (NullPointerException e) {
                        event.setDegree(null);
                        weatherEvents.add(event);
                    }
                }
            }
        }
        return weatherEvents;
    }

    public static void DataFile(ArrayList<WeatherEvents> weatherEvents) throws IOException {
        LocalDate LocalDate = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileWriter writer = new FileWriter("Datalakedir/" + LocalDate.now().format(formatter) + ".event");
        writer.write(new Gson().toJson(weatherEvents));
        writer.close();
        System.out.println(weatherEvents);
    }

}
