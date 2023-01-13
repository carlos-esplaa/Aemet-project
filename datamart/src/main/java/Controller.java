import com.google.gson.JsonArray;

import java.io.IOException;

public class Controller {
    public void controller() throws IOException {
        String dbPath = "C:\\Users\\fenix\\IdeaProjects\\Aemet-project\\Datamart.db";
        ReadEventsFiles readEventsFiles = new ReadEventsFiles();
        JsonArray content = readEventsFiles.getEventsListFromFile();
        DeserializerMaxFilter deserializerMax = new DeserializerMaxFilter();
        deserializerMax.getGcWeatherMaxEventsFromFile(content, dbPath);
        DeserializerMinFilter deserializerMinFilter = new DeserializerMinFilter();
        deserializerMinFilter.getGcWeatherMinEventsFromFile(content, dbPath);
    }
}
