import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReadEventsFiles {

    public JsonArray getEventsListFromFile() throws IOException {

        LocalDate LocalDate = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileReader reader = new FileReader("Datalakedir/" + LocalDate.now().format(formatter) + ".event");
        JsonReader reader1 = new JsonReader(reader);
        reader1.setLenient(true);
        JsonArray content = new Gson().fromJson(reader1, JsonArray.class);
        return content;
    }
}

