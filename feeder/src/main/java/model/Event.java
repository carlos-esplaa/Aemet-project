package model;

import view.WeatherExtractor;

public class Event extends WeatherExtractor {
    private Double Longitud;
    private Double Latitud;
    private String Moment;
    private String Station;
    private String Location;
    private Double Degree;


    public Event(String Moment, String Station, String Location, String degree){
        this.Moment = Moment;
        this.Station= Station;
        this.Location = Location;
        this.Degree = Degree;
        this.Longitud = Longitud;
        this.Latitud = Latitud;

    }
    public Event(){}

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

    public double getDegree() {
        return Degree;
    }

    public void setDegree(Double degree) {
        Degree = degree;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }

    public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double latitud) {
        Latitud = latitud;
    }

}
