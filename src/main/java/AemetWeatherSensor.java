import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class AemetWeatherSensor {
    private String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3Muc2FudGFuYS5lc3BsYTI2QGdtYWlsLmNvbSIsImp0aSI6ImY4YmU0ZjhjLWIyMzItNGRhNC04Nzc1LWMzMjgwM2VmNTY2OSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjczMjYyNTQwLCJ1c2VySWQiOiJmOGJlNGY4Yy1iMjMyLTRkYTQtODc3NS1jMzI4MDNlZjU2NjkiLCJyb2xlIjoiIn0._SWdpKDj0xQDQYjCrRn5k88M8rsK7JfpLDR_UyWQfac";

    public String DataUrl() throws IOException {
        String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
        String response = Jsoup.connect(url)
                .followRedirects(false)
                .timeout(6000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String dataUrl = jsonObject.get("datos").getAsString();
        return dataUrl;
    }
    public String getContent(String dataUrl) throws IOException {
        String content = Jsoup.connect(dataUrl)
                .followRedirects(false)
                .timeout(6000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
        return content;
    }
}
