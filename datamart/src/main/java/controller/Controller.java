package controller;

import com.google.gson.JsonArray;

import java.io.IOException;
import model.*;
import view.*;


public class Controller {
    public void controller() throws IOException {
        String dbPath = "Datamartdir/datamart.db";
        ReadEventsFiles readEventsFiles = new ReadEventsFiles();
        JsonArray content = readEventsFiles.getEventsListFromFile();
        DatamartManegement datamartManegement = new DatamartManegement();
        datamartManegement.datamartManegement();
        DeserializerMaxFilter deserializerMax = new DeserializerMaxFilter();
        deserializerMax.getGcWeatherMaxEventsFromFile(content, dbPath);
        DeserializerMinFilter deserializerMinFilter = new DeserializerMinFilter();
        deserializerMinFilter.getGcWeatherMinEventsFromFile(content, dbPath);
    }
}
