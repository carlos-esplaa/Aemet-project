package view;

import com.google.gson.Gson;
import model.AemetWeatherException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.*;
public class GcWeatherEventsFile {
   public void gcWeatherEventsFile(List<Event> AemetweatherEvents) throws AemetWeatherException {

        LocalDate LocalDate = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileWriter writer = null;
        try {
            writer = new FileWriter("Datalakedir/" + LocalDate.now().format(formatter) + ".event", true);
            writer.write(new Gson().toJson(AemetweatherEvents) + "\n");
            writer.close();
        } catch (IOException e) {
            throw new AemetWeatherException("No pudo escribir en el archivo");
        }
    }
}
