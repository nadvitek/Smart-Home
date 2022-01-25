package cz.cvut.k36.omo.hw.smarthome;

import cz.cvut.k36.omo.hw.appliances.*;
import cz.cvut.k36.omo.hw.events.Event;
import cz.cvut.k36.omo.hw.items.Item;
import cz.cvut.k36.omo.hw.livings.Cat;
import cz.cvut.k36.omo.hw.livings.Child;
import cz.cvut.k36.omo.hw.livings.Dad;
import cz.cvut.k36.omo.hw.livings.Dog;
import cz.cvut.k36.omo.hw.livings.Fish;
import cz.cvut.k36.omo.hw.livings.Grandparent;
import cz.cvut.k36.omo.hw.livings.Mom;
import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.livings.Pet;
import cz.cvut.k36.omo.hw.reports.Report;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for the house (Singleton).
 */
public class House {
    private static House INSTANCE;
    private HouseAPI myAPI;
    private final List<Floor> floors = new ArrayList();
    private Garden garden;
    private final List<Person> people = new ArrayList();
    private final List<Pet> pets = new ArrayList();
    private final Map<Person, List<Item>> items = new HashMap();
    private final List<ApplianceAPI> allAPIs = new ArrayList();
    private final Room voidRoom = new Room("Void", -1);
    private final List<Report> reports = new ArrayList();
    private final List<Event> events = new ArrayList();
    
    private House() {
    }

    /**
     * Method that returns the Instance of the house (Singleton).
     * @return - the Instance of the house (Singleton)
     */
    public synchronized static House getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new House();
        }
        return INSTANCE;
    }

    /**
     * Mathod that gives his own api.
     * @param hapi - api of the house
     */
    public void giveAPI(HouseAPI hapi){
        this.myAPI = hapi;
    }

    /**
     * Method that sets floor in the house.
     * @param floor - number of floor
     * @param rooms - array of rooms
     * @throws Exception
     */
    public void setFloor(int floor, int[] rooms) throws Exception {
        this.floors.add(new Floor(floor, rooms));
    }

    /**
     * Method that sets people living in the house.
     * @param adults - number of adults
     * @param children - number of children
     * @throws Exception
     */
    public void addHumans(int adults, int children) throws Exception {
        int a = 0;
        for (int i = 0; i < adults; i++) {
            Person person;
            Room room;
            if (a == 0) {
                person = new Dad("dad", this.myAPI, 39);
                room = getStartingRoom("Bedroom", 0);
            } else if (a == 1) {
                person = new Mom("mom", this.myAPI, 39);
                room = getStartingRoom("Bedroom", 1);
            } else if (a == 2) {
                person = new Grandparent("grandpa", this.myAPI, 48);
                room = getStartingRoom("Bedroom", -1);
            } else {
                person = new Grandparent("grandma", this.myAPI, 48);
                room = getStartingRoom("Bedroom", -2);
            }
            person.goToRoom(room);
            people.add(person);
            ++a;
        }
        int c = 2;
        for (int i = 0; i < children; i++) {
            String name = "";
            if (i % 2 == 0) {
                name = "daughter" + (i/2 + 1);
            } else {
                name = "son" + (i/2 + 1);
            }
            Person person = new Child(name, this.myAPI, 39);
            Room room = getStartingRoom("Bedroom", c);
            person.goToRoom(room);
            people.add(person);
            ++c;
        }
    }

    /**
     * Method that sets the area of the garden.
     * @param areaOfGarden - area of the garden
     */
    public void setAreaOfGarden(int areaOfGarden) {
        this.garden = new Garden(areaOfGarden);
    }

    /**
     * Method that adds animals to the house.
     * @param dogs - number of dogs
     * @param cats - number of cats
     * @param fish - number of fish
     * @throws Exception
     */
    public void addAnimals(int dogs, int cats, int fish) throws Exception {
        for (int i = 0; i < dogs; i++) {
            pets.add(new Dog(true, this.myAPI));
        }
        for (int i = 0; i < cats; i++) {
            pets.add(new Cat(true, this.myAPI));
        }
        for (int i = 0; i < fish; i++) {
            pets.add(new Fish(false, this.myAPI));
        }
        for (Pet pet : pets) {
            pet.goToRoom(getStartingRoom("Living room", 10));
        }
    }

    /**
     * Method that adds appliances to the house.
     */
    public void addAppliances() {
        LocksAPI locksAPI = new LocksAPI(new Locks(people, null), this);
        allAPIs.add(locksAPI);
        for (Floor floor : floors) {
            allAPIs.add(new VacuumAPI(new Vacuum(null, floor), this));
            for (Room room : floor.getRooms()) {
                DustSensorsAPI dustSensorsAPI = new DustSensorsAPI(new DustSensors(room), this);
                allAPIs.add(dustSensorsAPI);
                room.addApplianceToRoom(dustSensorsAPI);
                LightSensorsAPI newSensors = new LightSensorsAPI(new LightSensors(room), this);
                allAPIs.add(newSensors);
                room.addApplianceToRoom(newSensors);
                LightsAPI lightsAPI = new LightsAPI(new Lights(room), newSensors, this);
                allAPIs.add(lightsAPI);
                newSensors.setLights(lightsAPI);
                room.addApplianceToRoom(lightsAPI);

                HeatingAPI heatingAPI = new HeatingAPI(new Heating(room), this);
                allAPIs.add(heatingAPI);
                room.addApplianceToRoom(heatingAPI);
                if (room.getTypeOfRoom().equals("Living room")){
                    FishTankFilterAPI fishTankFilterAPI = new FishTankFilterAPI(new FishTankFilter(room), this);
                    allAPIs.add(fishTankFilterAPI);
                    room.addApplianceToRoom(fishTankFilterAPI);
                    TelevisionAPI televisionAPI = new TelevisionAPI(new Television(room), this);
                    allAPIs.add(televisionAPI);
                    room.addApplianceToRoom(televisionAPI);
                    HubAPI hubAPI = new HubAPI(new Hub(room), this);
                    allAPIs.add(hubAPI);
                    room.addApplianceToRoom(hubAPI);
                } else if (room.getTypeOfRoom().equals("Kitchen")){
                    DishwasherAPI dishwasherAPI = new DishwasherAPI(new Dishwasher(room), this);
                    allAPIs.add(dishwasherAPI);
                    room.addApplianceToRoom(dishwasherAPI);
                    FridgeAPI fridgeAPI = new FridgeAPI(new Fridge(room), this);
                    allAPIs.add(fridgeAPI);
                    room.addApplianceToRoom(fridgeAPI);
                    MicrowaveAPI microwaveAPI = new MicrowaveAPI(new Microwave(room), this);
                    allAPIs.add(microwaveAPI);
                    room.addApplianceToRoom(microwaveAPI);
                    OvenAPI ovenAPI = new OvenAPI(new Oven(room), this);
                    allAPIs.add(ovenAPI);
                    room.addApplianceToRoom(ovenAPI);
                } else if (room.getTypeOfRoom().equals("Bathroom")){
                    ToiletteAPI toiletteAPI = new ToiletteAPI(new Toilette(room), this);
                    allAPIs.add(toiletteAPI);
                    room.addApplianceToRoom(toiletteAPI);
                    WashingMachineAPI washingMachineAPI = new WashingMachineAPI(new WashingMachine(room), this);
                    allAPIs.add(washingMachineAPI);
                    room.addApplianceToRoom(washingMachineAPI);
                }
                else if (room.getTypeOfRoom().equals("Bedroom")){
                    BlindsAPI blindsAPI = new BlindsAPI(new Blinds(room), this);
                    allAPIs.add(blindsAPI);
                    room.addApplianceToRoom(blindsAPI);
                }
            }
        }
        LawnmowerAPI lawnmowerAPI = new LawnmowerAPI(new Lawnmower(null, garden.getArea()), this);
        allAPIs.add(lawnmowerAPI);
        garden.setLawnmowerAPI(lawnmowerAPI);
    }

    /**
     * toString method for HouseConfigurationReport.
     * @return - house written in String
     */
    @Override
    public String toString() {
        String ret = "";
        ret += "House has garden of area: " + this.garden.getArea() +  "\n";
        ret += "House has " + floors.size() + " floors\n";
        for (int i = 0; i < floors.size(); i++) {
            ret += "Floor number " + (i + 1) + " has:\n";
            for (int j = 0; j < floors.get(i).getRooms().size(); j++) {
                ret += floors.get(i).getRooms().get(j).getTypeOfRoom() + "\n";
            }
        }
        int old = 0;
        int young = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getClass().getSuperclass().getSimpleName().equals("Adult")){
                ++old;
            } else {
                ++young;
            }
        }
        ret += "There is " + people.size() + " people: " + old + " adults, " + young + " children.\n";
        int dog = 0;
        int cat = 0;
        int fish = 0;
        int parrot = 0;
        int hamster = 0;
        int snake = 0;
        for (int i = 0; i < pets.size(); i++) {
            switch (pets.get(i).getClass().getSimpleName()) {
                case "Dog" -> ++dog;
                case "Cat" -> ++cat;
                case "Fish" -> ++fish;
                case "Snake" -> ++snake;
                case "Hamster" -> ++hamster;
                case "Parrot" -> ++parrot;
                default -> {
                }
            }
        }
        ret += "There is " + pets.size() + " pets:\n" + dog + " dogs\n" + cat + " cats\n" + fish + " fish\n";
        ret += "Every member of the house has it's own Bike and Ski\n";
        /*int skies = 0;
        int bikes = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getClass().getSimpleName().equals("Ski")){
                ++skies;
            } else {
                ++bikes;
            }
        }
        ret += "There is " + items.size() + " items: " + skies + " skies, " + bikes + " bikes.\n";
        */
        ret += "There is a fridge, microwave, oven and dishwasher in each Kitchen,\n";
        ret += "a television, hub and fish tank filter in the Living room,\n";
        ret += "blinds in each Bedroom,\n";
        ret += "and a toilette and a washing machine in each Bathroom.\n";
        ret += "Each room has lights and sensors for lights and dust and heating.\n";
        ret += "Every floor has a vacuum and there is a lawnmower in the Garden.\n";
        ret += "The House has automatic locks.\n";
        return ret;
    }
    
    private Room getStartingRoom(String nameOfRoom, int personID) throws Exception {
        for (Floor floor : floors) {
            if (floor.getNumberOfFloor() == 1 && (personID == 1 || personID == 0)) {
                for (Room room : floor.getRooms()) {
                    if (room.getTypeOfRoom().equals(nameOfRoom)) {
                        return room;
                    }
                }
            } else if (floor.getNumberOfFloor() == 0 && (personID == -1 || personID == -2)) {
                for (Room room : floor.getRooms()) {
                    if (room.getTypeOfRoom().equals(nameOfRoom)) {
                        return room;
                    }
                }
            } else if (personID >= 2) {
                for (Room room : floor.getRooms()) {
                    if ((room.isRoomFree() || room.howManyPplInRoom() == 1) && room.getTypeOfRoom().equals(nameOfRoom)) {
                        return room;
                    }
                }
            } else {
                for (Room room : floor.getRooms()) {
                    if (room.getTypeOfRoom().equals(nameOfRoom)) {
                        return room;
                    }
                }
            }
        }
        throw new Exception("Room not found!\n");
    }

    /**
     * Method that adds new report to the reports.
     * @param report - new report
     */
    public void addNewReport(Report report) {
        reports.add(report);
    }

    /**
     * Method that returns hash map of items and their owners.
     * @return hash map of items and their owners
     */
    public Map<Person, List<Item>> getItems() {
        return this.items;
    }

    /**
     * Method that returns events that need to be solved in the house.
     * @return events that need to be solved in the house
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Method that returns all reports in the house.
     * @return all reports in the house
     */
    public List<Report> getReports(){
        return this.reports;
    }

    /**
     * Method that returns all floors in the house.
     * @return all floors in the house
     */
    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Method that returns all people in the house.
     * @return all people in the house
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Method that returns all pet in the house.
     * @return all pet in the house
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Method that returns a void room (outside).
     * @return a void room (outside)
     */
    public Room getVoidRoom() {
        return voidRoom;
    }

    /**
     * Method that returns all APIs of all appliances in the house.
     * @return all APIs of all appliances in the house
     */
    public List<ApplianceAPI> getAPIs() {
        return allAPIs;
    }
}
