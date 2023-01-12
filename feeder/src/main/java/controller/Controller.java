package controller;

import model.AemetWeatherEventsFilter;
import model.AemetWeatherException;
import view.GcWeatherEventsFile;
import view.WeatherExtractor;
import model.*;
import view.*;

import java.util.List;

public class Controller {
    public void controller() throws AemetWeatherException {
        WeatherExtractor weatherExtractor = new WeatherExtractor();
        String jsonElements = weatherExtractor.aemetWeatherExtractor();
        AemetWeatherEventsFilter aemetWeatherEventsFilter = new AemetWeatherEventsFilter();
        List<Event> aemetWeatherEventsList = aemetWeatherEventsFilter.getGcWeatherEvents(jsonElements);
        GcWeatherEventsFile gcWeatherEventsFile = new GcWeatherEventsFile();
        gcWeatherEventsFile.gcWeatherEventsFile(aemetWeatherEventsList);
    }
}