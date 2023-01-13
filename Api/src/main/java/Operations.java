import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {
    public String operation(List<EventsFromDatamart> eventsFromDatamartList, String from, String to){
        List<EventsFromDatamart> eventsFilter = eventsFromDatamartList.stream().filter(d -> d.getMoment().equals(from)
        && d.getMoment().equals(to)).collect(Collectors.toList());
        String filter = new Gson().toJson(eventsFilter);
        return filter;
    }
}
