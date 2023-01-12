package model;
import view.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;

public class AemetWeatherEventsFilter {
    public List<Event> getGcWeatherEvents(String content) {
        List<Event> aemetWeatherEventsFilterList = new ArrayList<>();
        JsonArray weatherEventsList = new Gson().fromJson(content, JsonArray.class);
        for (JsonElement item : weatherEventsList) {
            Event event = deserializer(item);
            if (event.getLongitud() > -16 && event.getLongitud() < -15) {
                if (event.getLatitud() > 27.5 && event.getLatitud() < 28.4) {
                    aemetWeatherEventsFilterList.add(event);
                }
            }
        }
        return aemetWeatherEventsFilterList;
    }
    private Event deserializer(JsonElement jsonElement) {
        Event event = new Event();
        event.setLongitud(jsonElement.getAsJsonObject().get("lon").getAsDouble());
        event.setLatitud(jsonElement.getAsJsonObject().get("lat").getAsDouble());
        event.setMoment(jsonElement.getAsJsonObject().get("fint").getAsString());
        event.setStation(jsonElement.getAsJsonObject().get("idema").getAsString());
        event.setLocation(jsonElement.getAsJsonObject().get("ubi").getAsString());
        try {
            event.setDegree(jsonElement.getAsJsonObject().get("ta").getAsDouble());
        } catch (NullPointerException e) {
            event.setDegree(null);
        }
        return event;
    }
}

