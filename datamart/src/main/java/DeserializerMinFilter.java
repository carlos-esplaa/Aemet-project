import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.time.LocalDate;

public class DeserializerMinFilter {
    public void getGcWeatherMinEventsFromFile(JsonArray content, String dbPath) {
        for (JsonElement jsonElement : content) {
            EventsFromFile eventsFromFileMin = new EventsFromFile();
            eventsFromFileMin.setMoment(jsonElement.getAsJsonObject().get("Moment").getAsString());
            eventsFromFileMin.setStation(jsonElement.getAsJsonObject().get("Station").getAsString());
            eventsFromFileMin.setLocation(jsonElement.getAsJsonObject().get("Location").getAsString());
            eventsFromFileMin.setDegree(jsonElement.getAsJsonObject().get("Degree").getAsDouble());
            String day = String.valueOf(LocalDate.now());
            DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
            datamartDegreesCreator.insertToMinDegree(eventsFromFileMin.getMoment(), day, eventsFromFileMin.getLocation(),
                    eventsFromFileMin.getStation(), eventsFromFileMin.getDegree(), dbPath);

        }
    }
}

