package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class User {
    public String name;
    public String idUser;
    public String surname;
    private List<Bike> bikeListUser;

    public User(){
    }

    public User(String name, String idUser, String surname) {
        this.name = name;
        this.idUser = idUser;
        this.surname = surname;
        this.bikeListUser = new LinkedList<Bike>();
    }

    public void addBikeUser(Bike bike){
        this.bikeListUser.add(bike);
    }

    public List<Bike> getBikeListUser(){
        return bikeListUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
