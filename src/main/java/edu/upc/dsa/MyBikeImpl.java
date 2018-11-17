package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class MyBikeImpl implements MyBike{
    private Station[] stationArray;
    private Map<String, User> userMap;

    private int stationNumber;

    final Logger log = Logger.getLogger(MyBikeImpl.class);

    //Singleton
    private static MyBikeImpl ourInstance;


    public static MyBikeImpl getInstance() {
        if (ourInstance==null)
            ourInstance = new MyBikeImpl();
        return ourInstance;
    }

    private MyBikeImpl(){
        this.userMap = new HashMap<String, User>();
        this.stationArray = new Station[100];
        this.stationNumber = 0;
    }

    public void clear(){
        ourInstance = new MyBikeImpl();
    }

    //  /Singleton


    public int findStation(String idStation){
        int i = 0;
        boolean f = false;
        while(!f && i < stationNumber){
            if(stationArray[i].getIdStation().equals((idStation))){
                f=true;
            } else{
                i++;
            }
        }
        if (f==true){
            return i;
        } else{
            return -1;
        }
    }

    public void addUser(String idUser, String name, String surname){
        User x = new User(idUser,name,surname);
        userMap.put(x.getName(), x);
        log.info("User " +name + " " + surname +" added succesfully");
    }

    public void addStation(String idStation, String description, int max, double lat, double lon){
        Station x = new Station(idStation,description,max,lat,lon);
        int i = 0;
        boolean f = false;
        while(!f && i < stationArray.length){
            if(stationArray[i] == null){
                f=true;
            } else{
                i++;
            }
        }
        if(f==true){
            stationArray[i] = x;
            stationNumber++;
            log.info("Station " +idStation+" added succesfully");
        } else{
            log.warn("Station " +idStation+" could not be added: array is full");
        }
    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException{
        Bike x = new Bike(idBike,description,kms,idStation);
        int i = findStation(idStation);
        if(i!=-1){
            int ok = stationArray[i].addBikeStation(x);
            if(ok == 0){
                log.warn("Bike  " +idBike+" could not be added: station is full");
                throw new StationFullException("Station full");
            } else{
                log.info("Bike  " +idBike+" added succesfully to station: " +stationArray[i].getIdStation());
            }
        } else{
            log.warn("Bike  " +idBike+" could not be added: station does not exist");
            throw new StationNotFoundException("Could not find this station");
        }
    }

    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException{
        int i = findStation(idStation);
        if (i!=-1){
            List<Bike> bikesByStation = stationArray[i].getBikeListStation();
            Comparator<Bike> Comparador1 = new Comparator<Bike>() {
                public int compare(Bike o1, Bike o2) {
                    if(o1.getKms()>= o2.getKms())
                        return 0;
                    else return -1;
                }
            };
            bikesByStation.sort(Comparador1);
            return bikesByStation;
        } else{
            log.warn("Station doesnt exist");
            throw new StationNotFoundException("Could not find this station");
        }

    }

    public Bike getBike(String idStation, String userId) throws UserNotFoundException, StationNotFoundException{
        int i = findStation(idStation);
        if(i!=-1){
            User user = this.userMap.get(userId);
            if(user == null){
                log.warn("User does not exist");
                throw new UserNotFoundException("Could not find this user");
            } else{
                Bike x = stationArray[i].getBikeStation();
                user.addBikeUser(x);
                log.info("Bike added correctly");
                return x;
            }
        } else{
            log.warn("Bike could not be added: station does not exist");
            throw new StationNotFoundException("Could not find this station");
        }
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException{
        User user = this.userMap.get(userId);
        if(user == null){
            log.warn("User does not exist");
            throw new UserNotFoundException("Could not find this user");
        } else {
            return user.getBikeListUser();
        }
    }

    public int numUsers(){
        return userMap.size();
    }

    public int numStations(){
        return stationNumber;
    }

    public int numBikes(String idStation){
        int i = findStation(idStation);
        return stationArray[i].getBikeListStation().size();
    }


}
