package view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.AemetWeatherException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


public class WeatherExtractor {
   public String aemetWeatherExtractor() throws AemetWeatherException {
       String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3Muc2FudGFuYS5lc3BsYTI2QGdtYWlsLmNvbSIsImp0aSI6ImY4YmU0ZjhjLWIyMzItNGRhNC04Nzc1LWMzMjgwM2VmNTY2OSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjczMjYyNTQwLCJ1c2VySWQiOiJmOGJlNGY4Yy1iMjMyLTRkYTQtODc3NS1jMzI4MDNlZjU2NjkiLCJyb2xlIjoiIn0._SWdpKDj0xQDQYjCrRn5k88M8rsK7JfpLDR_UyWQfac";
       String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";

       try {
           String response = Jsoup.connect(url)
                   .followRedirects(false)
                   .ignoreContentType(true)
                   .header("accept", "application/json")
                   .header("api_key", apiKey)
                   .method(Connection.Method.GET)
                   .maxBodySize(0).execute().body();
           JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
           String dataUrl = jsonObject.get("datos").getAsString();

           String content = Jsoup.connect(dataUrl)
                   .followRedirects(false)
                   .ignoreContentType(true)
                   .header("accept", "application/json")
                   .header("api_key", apiKey)
                   .method(Connection.Method.GET)
                   .maxBodySize(0).execute().body();
           return content;

       } catch (IOException e) {
           throw new AemetWeatherException("No se puedo obtener los datos");
       }
   }
}
