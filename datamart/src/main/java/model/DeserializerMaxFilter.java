package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import view.DatamartDegreesCreator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeserializerMaxFilter {

    public void getGcWeatherMaxEventsFromFile(JsonArray content, String dbPath) {
        List<EventsFromFile> eventsFromFilesList = new ArrayList<>();
        DatamartDegreesCreator datamartDegreesCreator = new DatamartDegreesCreator();
        EventsFromFile maxDegreesFromFile = null;
        for (JsonElement jsonElement : content) {
            EventsFromFile eventsFromFile = deserializer(jsonElement);
            eventsFromFilesList.add(eventsFromFile);
            maxDegreesFromFile = eventsFromFilesList.stream().max(
                    Comparator.comparing(EventsFromFile::getDegree)).get();
        }
        datamartDegreesCreator.insertTomaxDegrees(maxDegreesFromFile.getMoment(),
                maxDegreesFromFile.getLocation(), maxDegreesFromFile.getStation(),
                maxDegreesFromFile.getDegree(), dbPath);
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