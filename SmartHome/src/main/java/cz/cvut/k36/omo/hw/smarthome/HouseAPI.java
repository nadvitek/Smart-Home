package cz.cvut.k36.omo.hw.smarthome;

import cz.cvut.k36.omo.hw.appliances.ApplianceAPI;
import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.livings.Pet;
import cz.cvut.k36.omo.hw.reports.*;

import java.util.Random;

/**
 * Class for API of the house.
 */
public class HouseAPI {
    private final int numberOfDays = 13;
    private final House myHouse;

    /**
     * Constructor - sets up house and starts life.
     * @param numberOfConfiguration - number of configuration
     * @throws Exception
     */
    public HouseAPI(int numberOfConfiguration) throws Exception {
        this.myHouse = setupHouse(numberOfConfiguration);
        addReport(new HouseConfigurationReport(this.myHouse));
        startLife();
    }

    /**
     * Method that returns the house.
     * @return the house
     */
    public House getHouse() {
        return this.myHouse;
    }

    /**
     * Method that adds report to the house.
     * @param report - new report
     */
    public void addReport(Report report) {
        myHouse.addNewReport(report);
    }

    /**
     * Method for person entering the house.
     * @param person that enters the house
     */
    public void enterHouse(Person person) {
        myHouse.getVoidRoom().personLeavesRoom(person);
    }

    /**
     * Method that returns the room that I need.
     * @param numberOfFloor - number of floor where the room is
     * @param typeOfRoom - name of the room
     * @return room that I need
     * @throws Exception
     */
    public Room getRoom(int numberOfFloor, String typeOfRoom) throws Exception {
        while(true) {
            for (Room room : myHouse.getFloors().get(numberOfFloor).getRooms()) {
                if (typeOfRoom.equals(room.getTypeOfRoom())) {
                    return room;
                }
            }
            --numberOfFloor;
            if (numberOfFloor < 0){
                throw new Exception("Room not found!\n");
            }
        }
    }

    /**
     * Method that gets the person out of the house.
     * @param person - person that leaves the house
     */
    public void getOutOfTheHouse(Person person) {
        person.goToRoom(this.myHouse.getVoidRoom());
    }

    /**
     * Method that returns a random room.
     * @return a random room
     */
    public Room getRandomRoom() {
        Random rand = new Random();
        Floor floor = this.myHouse.getFloors().get(rand.nextInt(this.myHouse.getFloors().size()));
        return floor.getRooms().get(rand.nextInt(floor.getRooms().size()));
    }

    private void useTime(int time) throws Exception {
        if ((time % Times.WEEK) % Times.DAY == 0){
            myHouse.addNewReport(new DayReport(time));
        }
        for (ApplianceAPI api : this.myHouse.getAPIs()) {
            api.useTime(time);
        }
        for (Person person : this.myHouse.getPeople()) {
            person.useTime(time);
        }
        for (Pet pet : this.myHouse.getPets()) {
            pet.useTime();
        }
    }

    private House setupHouse(int configuration) throws Exception {
        House myHouse = House.getInstance();
        myHouse.giveAPI(this);
        ConfigLoader cl = new ConfigLoader(myHouse, configuration);
        return cl.getHouse();
    }

    private void startLife() throws Exception {
        for (int i = 0; i < numberOfDays*24*6; i++) {
            useTime(i);
        }
        addReport(new ConsumptionReport(this.myHouse.getAPIs()));
        addReport(new ActivityAndUsageReport(this.myHouse.getAPIs()));
        new Saver(this.myHouse.getReports());
    }
}
