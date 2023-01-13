import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.time.LocalDate;

public class DeserializerMaxFilter {

    public void getGcWeatherMaxEventsFromFile(JsonArray content, String dbPath) {
        for (JsonElement jsonElement : content) {
            EventsFromFile eventsFromFileMax = new EventsFromFile();
                eventsFromFileMax.setMoment(jsonElement.getAsJsonObject().get("Moment").getAsString());
                eventsFromFileMax.setStation(jsonElement.getAsJsonObject().get("Station").getAsString());
                eventsFromFileMax.setLocation(jsonElement.getAsJsonObject().get("Location").getAsString());
                eventsFromFileMax.setDegree(jsonElement.getAsJsonObject().get("Degree").getAsDouble());
                String day = String.valueOf(LocalDate.now());
                DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
                datamartDegreesCreator.insertToMaxDegree(eventsFromFileMax.getMoment(), day, eventsFromFileMax.getLocation(),
                        eventsFromFileMax.getStation(), eventsFromFileMax.getDegree(), dbPath);
            try {
                eventsFromFileMax.setDegree(jsonElement.getAsJsonObject().get("Degree").getAsDouble());
            } catch (NullPointerException e) {
                eventsFromFileMax.setDegree(null);
            }

        }
    }
}