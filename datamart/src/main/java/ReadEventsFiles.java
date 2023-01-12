import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadEventsFiles {

    public List<ReadEventsFiles> getEventsListFromFile() throws IOException {
        List<ReadEventsFiles> readEventsFilesList = new ArrayList<>();
        LocalDate LocalDate = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileReader reader = new FileReader("Datalakedir/" + LocalDate.now().format(formatter) + ".event");

        return readEventsFilesList;
    }
}
