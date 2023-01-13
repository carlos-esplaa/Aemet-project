package controller;
import model.*;
import view.*;
import java.util.List;
public class Controller {
    public String getMinWeather(String from, String to){
        DatamartExtractor datamartExtractor = new DatamartExtractor();
        datamartExtractor.connect();
        List<EventsFromDatamart> minWeathersList = datamartExtractor.selectMinAll();
        Operations operations = new Operations();
        String weathersFilter = operations.operation(minWeathersList, from, to);
        System.out.println("llega a minweather");
        return weathersFilter;
    }
    public String getMaxWeather(String from, String to){
        DatamartExtractor datamartExtractor = new DatamartExtractor();
        datamartExtractor.connect();
        List<EventsFromDatamart> maxWeathersList = datamartExtractor.selectMaxAll();
        Operations operations = new Operations();
        String weathersFilter = operations.operation(maxWeathersList, from, to);
        return weathersFilter;
    }
}
