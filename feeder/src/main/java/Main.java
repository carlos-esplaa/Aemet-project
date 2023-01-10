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

public class Main {
        public static void main(String[] args) throws Exception {
                String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3Muc2FudGFuYS5lc3BsYTI2QGdtYWlsLmNvbSIsImp0aSI6ImY4YmU0ZjhjLWIyMzItNGRhNC04Nzc1LWMzMjgwM2VmNTY2OSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjczMjYyNTQwLCJ1c2VySWQiOiJmOGJlNGY4Yy1iMjMyLTRkYTQtODc3NS1jMzI4MDNlZjU2NjkiLCJyb2xlIjoiIn0._SWdpKDj0xQDQYjCrRn5k88M8rsK7JfpLDR_UyWQfac";
                String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
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

                JsonArray jsonArray = new Gson().fromJson(content, JsonArray.class);
                JsonArray gcEvents = new JsonArray();
                for (JsonElement jsonElement : jsonArray){
                        if (jsonElement.getAsJsonObject().get("lon").getAsDouble() < -15 && jsonElement.getAsJsonObject().get("lon").getAsDouble() > -16
                                && jsonElement.getAsJsonObject().get("lat").getAsDouble() < 28.4 && jsonElement.getAsJsonObject().get("lat").getAsDouble() > 27.5){
                                String Moment = jsonElement.getAsJsonObject().get("fint").getAsString();
                                String Station = jsonElement.getAsJsonObject().get("idema").getAsString();
                                String Location = jsonElement.getAsJsonObject().get("ubi").getAsString();
                                gcEvents.add(Moment + Station + Location);
                                try{
                                        Double Degree = jsonElement.getAsJsonObject().get("ta").getAsDouble();
                                        gcEvents.add(Degree);
                                } catch (NullPointerException e){
                                        Double Degree = null;
                                }

                        }
                }
                System.out.println(gcEvents.toString());
                LocalDate LocalDate = java.time.LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                FileWriter writer = new FileWriter("Datalakedir/" + LocalDate.now().format(formatter) + ".event");
                writer.write(gcEvents.toString());
                writer.close();
        }
}


