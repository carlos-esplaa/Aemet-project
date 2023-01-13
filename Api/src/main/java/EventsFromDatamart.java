public class EventsFromDatamart {
    private String Moment;
    private String Station;
    private String Location;
    private Double Degree;
    private String time;

    public EventsFromDatamart(String Moment, String Station, String Location, Double Degree, String time){
        this.Moment = Moment;
        this.Location = Location;
        this.Station = Moment;
        this.Degree = Degree;
        this.time = time;
    }

    public String getMoment() {
        return Moment;
    }

    public void setMoment(String moment) {
        Moment = moment;
    }

    public String getStation() {
        return Station;
    }

    public void setStation(String station) {
        Station = station;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Double getDegree() {
        return Degree;
    }

    public void setDegree(Double degree) {
        Degree = degree;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public EventsFromDatamart(){}
}
