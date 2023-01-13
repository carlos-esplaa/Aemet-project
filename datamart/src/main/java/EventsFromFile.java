public class EventsFromFile {
    private String Moment;
    private String Station;
    private String Location;
    private Double Degree;

    public EventsFromFile(String Moment, String Station, String Location, Double Degree){
        this.Moment = Moment;
        this.Location = Location;
        this.Station = Moment;
        this.Degree = Degree;
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
    public EventsFromFile(){}

    @Override
    public String toString() {
        return "Event{" +
                "Moment='" + Moment + '\'' +
                ", Station='" + Station + '\'' +
                ", Location='" + Location + '\'' +
                ", Degree=" + Degree +
                '}';
    }
}

