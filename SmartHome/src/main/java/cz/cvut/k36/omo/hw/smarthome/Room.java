package cz.cvut.k36.omo.hw.smarthome;

import cz.cvut.k36.omo.hw.appliances.*;
import cz.cvut.k36.omo.hw.items.Item;
import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.events.Event;
import cz.cvut.k36.omo.hw.livings.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for room.
 */
public class Room {
    private List<Person> people = new ArrayList();
    private List<ApplianceAPI> applianceAPIs = new ArrayList();
    private List<Pet> pets = new ArrayList();
    private String typeOfRoom;
    private final int numberOfFloor;

    /**
     * Constructor.
     * @param nameOfRoom - name of this room
     * @param numberOfFloor - number of floor where the room is
     */
    public Room(String nameOfRoom, int numberOfFloor) {
        this.typeOfRoom = nameOfRoom;
        this.numberOfFloor = numberOfFloor;
        
    }

    /**
     * Method that returns the name of the room.
     * @return the name of the room
     */
    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    /**
     * Method for person entering the room.
     * @param person - person that enters the room
     */
    public void personInRoom(Person person) {
        people.add(person);
        if (applianceAPIs.size() > 1) {
            LightSensorsAPI lightSensorsAPI = (LightSensorsAPI) applianceAPIs.get(1);
            lightSensorsAPI.sensorsCatchMovement();
        }
    }

    /**
     * Method for pet entering the room.
     * @param pet - pet that enters the room
     */
    public void petInRoom(Pet pet) {
        this.pets.add(pet);
        if (applianceAPIs.size() > 0) {
            DustSensorsAPI dustSensorsAPI = (DustSensorsAPI) applianceAPIs.get(0);
            dustSensorsAPI.setDustInRoom(dustSensorsAPI.getDustInRoom() + 3);
        }
    }

    /**
     * Method for pet leaving the room.
     * @param pet - pet that leaves the room
     */
    public void petLeavesRoom(Pet pet){
        this.pets.remove(pet);
    }

    /**
     * Method for person leaving the room.
     * @param person - person that leaves the room
     */
    public void personLeavesRoom(Person person) {
        this.people.remove(person);
        if (isRoomFree() && !this.typeOfRoom.equals("Void") && applianceAPIs.size() > 2){
            LightsAPI lightsAPI = (LightsAPI) applianceAPIs.get(2);
            lightsAPI.turnOff();
        }
    }

    /**
     * Method to find out whether the room is empty.
     * @return true, if it's empty
     */
    public boolean isRoomFree() {
        return this.people.isEmpty();
    }

    /**
     * Method to find out how many people are in the room.
     * @return integer - people in the room
     */
    public int howManyPplInRoom() {
        return this.people.size();
    }

    /**
     * Method to get the number of floor the room is in.
     * @return integer - number of floor
     */
    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    /**
     * Method to get list of applianceAPIs in the room.
     * @return list of ApplianceAPI
     */
    public List<ApplianceAPI> getApplianceAPIs() {
        return applianceAPIs;
    }

    /**
     * Method to add an applianceAPI to room.
     * @param applianceAPI - applianceAPI to add to room
     */
    public void addApplianceToRoom(ApplianceAPI applianceAPI){
        this.applianceAPIs.add(applianceAPI);
    }

    /**
     * Method to check if the room is dirty.
     * @return true if the room is dirty
     */
    public boolean isRoomDirty() {
        if (applianceAPIs.size() > 0) {
            DustSensorsAPI dustSensorsAPI = (DustSensorsAPI) applianceAPIs.get(0);
            if (dustSensorsAPI.getDustInRoom() >= 20) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to clean the room.
     */
    public void cleanRoom(){
        if (applianceAPIs.size() > 0) {
            DustSensorsAPI dustSensorsAPI = (DustSensorsAPI) applianceAPIs.get(0);
            dustSensorsAPI.setDustInRoom(0);
        }
    }

    /**
     * Method to use the toilette.
     * @param name - of person using it
     */
    public void useWC(String name) {
        if (applianceAPIs.size() > 4) {
            ToiletteAPI toiletteAPI = (ToiletteAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("ToiletteAPI")).findAny().orElse(null);
            //ToiletteAPI toiletteAPI = (ToiletteAPI) applianceAPIs.get(4);
            toiletteAPI.useToilette(name);
        }
    }

    /**
     * Method to fill washing machine.
     * @param numOfClothes - how much the washing machine is filled
     */
    public void fillWashingMachine(int numOfClothes) {
        if (applianceAPIs.size() > 5) {
            WashingMachineAPI washingMachineAPI = (WashingMachineAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("WashingMachineAPI")).findAny().orElse(null);
            //WashingMachineAPI washingMachineAPI = (WashingMachineAPI) applianceAPIs.get(5);
            washingMachineAPI.fillWashingMachine(numOfClothes);
        }
    }

    /**
     * Method to fill the dishwasher.
     * @param numOfDishes - how much the dishwasher is filled
     */
    public void fillDishwasher(int numOfDishes) {
        if (applianceAPIs.size() > 4) {
            //DishwasherAPI dishwasherAPI = (DishwasherAPI) applianceAPIs.get(4);
            DishwasherAPI dishwasherAPI = (DishwasherAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("DishwasherAPI")).findAny().orElse(null);
            dishwasherAPI.fillDishwasher(numOfDishes);
        }
    }

    /**
     * Method to watch tv.
     * @param name - of person watching
     */
    public void watchTV(String name) {
        if (applianceAPIs.size() > 5) {
            //TelevisionAPI televisionAPI = (TelevisionAPI) applianceAPIs.get(5);
            TelevisionAPI televisionAPI = (TelevisionAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("TelevisionAPI")).findAny().orElse(null);
            televisionAPI.watchTV(name);
        }
    }

    /**
     * Method for listening to music.
     * @param name - of person listening
     */
    public void listenToMusic(String name) {
        if (applianceAPIs.size() > 6) {
            HubAPI hubAPI = (HubAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("HubAPI")).findAny().orElse(null);
            //HubAPI hubAPI = (HubAPI) applianceAPIs.get(7);
            hubAPI.listenToMusic(name);
        }
    }

    /**
     * Method to get list of poeple in the room.
     * @return list of People
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Method for using microwave.
     * @param name - of person using it
     */
    public void useMicrowave(String name) {
        //MicrowaveAPI microwaveAPI = (MicrowaveAPI) applianceAPIs.get(6);
        MicrowaveAPI microwaveAPI = (MicrowaveAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("MicrowaveAPI")).findAny().orElse(null);

        microwaveAPI.useMicrowave(name);
    }

    /**
     * Method for using oven.
     * @param name - of person using it
     */
    public void useOven(String name) {
        //OvenAPI ovenAPI = (OvenAPI) applianceAPIs.get(7);
        OvenAPI ovenAPI = (OvenAPI) applianceAPIs.stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("OvenAPI")).findAny().orElse(null);
        ovenAPI.useOven(name);
    }
}
