package edu.upc.dsa;

import java.util.*;

public class Station {
    public String idStation;
    public String description;
    public int max;
    public double lat;
    public double lon;
    private List<Bike> bikeListStation;

    public Station(String idStation, String description, int max, double lat, double lon) {
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        this.bikeListStation = new LinkedList<Bike>();
    }

    public Station(){
    }

    public List<Bike> getBikeListStation() {
        return bikeListStation;
    }

    public int addBikeStation(Bike bike){
        if(this.bikeListStation.size()<this.max){
            this.bikeListStation.add(bike);
            return 1;
        } else{
            return 0;
        }
    }

    public Bike getBikeStation(){
        if(bikeListStation.size()==0){
            return null;
        } else{
            Bike x = bikeListStation.get(0);
            bikeListStation.remove(0);
            return x;
        }
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
