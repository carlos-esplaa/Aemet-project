package model;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import view.*;
public class DeserializerMinFilter {
    public void getGcWeatherMinEventsFromFile(JsonArray content, String dbPath) {
        List<EventsFromFile> eventsFromFilesMin = new ArrayList<>();
        DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
        EventsFromFile minDegreesFromFile = null;
        for (JsonElement jsonElement : content) {
            EventsFromFile eventsFromFile = deserializer(jsonElement);
            eventsFromFilesMin.add(eventsFromFile);
            minDegreesFromFile = eventsFromFilesMin.stream().min(
                    Comparator.comparing(EventsFromFile::getDegree)).get();

        }
        datamartDegreesCreator.insertTominDegrees(minDegreesFromFile.getMoment(), minDegreesFromFile.getLocation(),
                minDegreesFromFile.getStation(), minDegreesFromFile.getDegree(), dbPath);
    }
    private EventsFromFile deserializer(JsonElement jsonElement) {
        EventsFromFile event = new EventsFromFile();
        event.setMoment(jsonElement.getAsJsonObject().get("Moment").getAsString().substring(0,10));
        event.setTime(jsonElement.getAsJsonObject().get("Moment").getAsString().substring(11));
        event.setStation(jsonElement.getAsJsonObject().get("Station").getAsString());
        event.setLocation(jsonElement.getAsJsonObject().get("Location").getAsString());
        try {
            event.setDegree(jsonElement.getAsJsonObject().get("Degree").getAsDouble());
        } catch (NullPointerException e) {
            event.setDegree(null);
        }
        return event;
    }
}

