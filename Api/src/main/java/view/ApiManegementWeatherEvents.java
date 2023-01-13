package view;
import controller.*;
import static spark.Spark.get;
public class ApiManegementWeatherEvents {
    public void start(){
        get("/v1/places/with-min-temperature", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            Controller controller = new Controller();
            String min = controller.getMinWeather(from, to);
            return min;
        });
        get("/v1/places/with-max-temperature", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            Controller controller = new Controller();
            String max = controller.getMaxWeather(from, to);
            return max;
        });
    }
}

