package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.appliances.FridgeAPI;
import cz.cvut.k36.omo.hw.events.Event;
import cz.cvut.k36.omo.hw.events.EventIterator;
import cz.cvut.k36.omo.hw.items.Bicycle;
import cz.cvut.k36.omo.hw.items.Item;
import cz.cvut.k36.omo.hw.items.Ski;
import cz.cvut.k36.omo.hw.reports.EventReport;
import cz.cvut.k36.omo.hw.smarthome.HouseAPI;
import cz.cvut.k36.omo.hw.smarthome.Room;
import cz.cvut.k36.omo.hw.smarthome.Times;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class for the people living in the house.
 */
public abstract class Person {
    private final String typeOfPerson;
    private EventIterator eventIterator;
    private Room myRoom;
    private Room myBedroom;
    private Event eventNowSolving;
    private boolean isBusy = false;
    private boolean solvingEvents = false;
    private boolean isAsleep = true;
    private boolean isGone = false;
    private int timeToFinish = 0;
    private int mealsInADay = 0;
    private final HouseAPI house;
    private int wakeUpTime;

    /**
     * Constructor.
     * @param typeOfPerson - name of a person
     * @param house - the house
     * @param wakeUpTime - time when they wake up
     */
    public Person(String typeOfPerson, HouseAPI house, int wakeUpTime) {
        this.typeOfPerson = typeOfPerson;
        this.house = house;
        this.wakeUpTime = wakeUpTime;
    }

    /**
     * Method for using time:
     * <ul>
     * <li>people use Toilet sometimes<li/>
     * <li>people goes to bed when it is 22:00<li/>
     * <li>people eat in a day when they haven't yet (3 times a day)<li/>
     * <li>people enjoy free time<li/>
     * <li>when they solve events, are out of the house, are busy or asleep, they don't do these above<li/>
     * <ul/>
     * @param time what time is it
     * @throws Exception
     */
    public void useTime(int time) throws Exception {
        int daytime = (time % Times.WEEK) % Times.DAY;
        if (!noActionIfs(daytime)) {
            if (generateNumber(80) < 11) {
                useToilet();
            }
            if(daytime >= Times.NIGHT_TIME || daytime < wakeUpTime) {
                this.goToBed();
            } else if (!haveEaten(daytime)){
                goEat();
            } else {
                enjoyFreeTime(daytime);
            }
        }
    }

    /**
     * Method that returns room that the person is now in.
     * @return room that the person is now in
     */
    public Room getRoom() {
        return this.myRoom;
    }

    /**
     * Method that tells if the person is out of the house.
     * @return true if the person is outside
     */
    public boolean isGone() {
        return isGone;
    }

    /**
     * Method that moves the person into some room (sets his tree bedroom if it's not yet).
     * @param room - room that he is forced to go to
     */
    public void goToRoom(Room room) {
        if (myBedroom == null) {
            myBedroom = room;
            myRoom = myBedroom;
        }
        this.myRoom.personLeavesRoom(this);
        this.myRoom = room;
        this.myRoom.personInRoom(this);
    }

    /**
     * Method for person leaving the house.
     * @param timeToFinish - time that tells how long the person will be gone
     */
    public void leaveHouse(int timeToFinish) {
        this.isGone = true;
        this.timeToFinish = timeToFinish;
        myRoom.personLeavesRoom(this);
        this.house.getOutOfTheHouse(this);
    }

    /**
     * Method that sets time when the person wakes up to the weekend wake up time (2 hours later).
     * @param isWeekend - tells if it's weekend or not
     */
    public void setWeekendWakeUpTime(boolean isWeekend) {
        if (isWeekend) {
            this.wakeUpTime += 12;
        } else {
            this.wakeUpTime -= 12;
        }
    }

    private void goToBed() throws Exception {
        useToilet();
        fillWashingMachine(generateNumber(2) + 1);
        if (!myRoom.equals(this.myBedroom)) {
            myRoom.personLeavesRoom(this);
            goToRoom(myBedroom);
        }
        this.isAsleep = true;
    }

    private void wakeUp() throws Exception {
        this.isAsleep = false;
        this.mealsInADay = 0;
        useToilet();
        goEat();
        this.isBusy = true;
        timeToFinish = 2;
    }

    private void enjoyFreeTime(int daytime) throws Exception {
        int num = generateNumber(70);
        if (num < 5 && daytime < Times.SUNFALL) {
            rideABike();
        } else if (num < 10 && daytime < Times.SUNFALL) {
            goSkiing();
        } else if (num < 20) {
            watchTV();
        } else if (num < 30) {
            goToGym();
        } else if (num < 50) {
            listenMusic();
        } else {
            solveEvents(daytime);
        }
    }

    private void solveEvents(int time) {
        solvingEvents = true;
        int num;
        if (this.getClass().getSimpleName().equals("Child")) {
            num = 1;
        } else {
            num = 2;
        }
        eventIterator = new EventIterator(this.house.getHouse().getEvents(), num);

        solveAnotherEvent(time);
    }

    private void solveAnotherEvent(int daytime){
        if (eventIterator.hasNext()){
            eventNowSolving = eventIterator.next();
            timeToFinish = eventNowSolving.getTimeToFinish();
            this.house.addReport(new EventReport(eventNowSolving.eventSolved(this.typeOfPerson), daytime, typeOfPerson, eventNowSolving.getRoom(), 0));
            if (eventNowSolving.getApi().getClass().getSimpleName().equals("FridgeAPI") && eventNowSolving.getExtra() == 2){
                leaveHouse(timeToFinish);
            } else {
                if (eventNowSolving.getRoom() != null) {
                    this.goToRoom(eventNowSolving.getRoom());
                }
            }
        } else {
            solvingEvents = false;
        }
    }

    private void rideABike() throws Exception {
        Map<Person, List<Item>> houseItems = this.house.getHouse().getItems();
        Bicycle bike = new Bicycle(this);
        if (!houseItems.containsKey(this)){
            houseItems.put(this, new ArrayList());
            houseItems.get(this).add(bike);
        } else if (houseItems.get(this).size() == 1) {
            if (houseItems.get(this).get(0).getClass().getSimpleName().equals("Bicycle")) {
                bike = (Bicycle)houseItems.get(this).get(0);
            } else {
                houseItems.get(this).add(bike);
            }
        } else {
            if (houseItems.get(this).get(0).getClass().getSimpleName().equals("Bicycle")) {
                bike = (Bicycle)houseItems.get(this).get(0);
            } else{
                bike = (Bicycle)houseItems.get(this).get(1);
            }
        }
        fillWashingMachine(generateNumber(2) + 2);
        leaveHouse(bike.getUsingTime());
    }

    private void goSkiing() throws Exception {
        Map<Person, List<Item>> houseItems = this.house.getHouse().getItems();
        Ski ski = new Ski(this);
        if (!houseItems.containsKey(this)){
            houseItems.put(this, new ArrayList());
            houseItems.get(this).add(ski);
        } else if (houseItems.get(this).size() == 1) {
            if (houseItems.get(this).get(0).getClass().getSimpleName().equals("Ski")) {
                ski = (Ski)houseItems.get(this).get(0);
            } else {
                houseItems.get(this).add(ski);
            }
        } else {
            if (houseItems.get(this).get(0).getClass().getSimpleName().equals("Ski")) {
                ski = (Ski)houseItems.get(this).get(0);
            } else{
                ski = (Ski)houseItems.get(this).get(1);
            }
        }
        fillWashingMachine(generateNumber(2) + 2);
        leaveHouse(ski.getUsingTime());
    }

    private void watchTV() throws Exception {
        this.goToRoom(this.house.getRoom(myRoom.getNumberOfFloor(), "Living room"));
        myRoom.watchTV(this.typeOfPerson);
        this.isBusy = true;
        this.timeToFinish = 6;
    }

    private void goToGym() throws Exception {
        fillWashingMachine(generateNumber(2) + 2);
        leaveHouse(6);
    }

    private void listenMusic() throws Exception {
        this.isBusy = true;
        this.timeToFinish = 6;
        Room livingRoom = this.house.getRoom(myRoom.getNumberOfFloor(), "Living room");
        this.goToRoom(livingRoom);
        livingRoom.listenToMusic(this.typeOfPerson);
    }

    private boolean haveEaten(int daytime) {
        return (daytime - mealsInADay * 36 < 36);
    }

    private void useToilet() throws Exception {
        Room toilet = this.house.getRoom(myRoom.getNumberOfFloor(), "Bathroom");
        this.goToRoom(toilet);
        toilet.useWC(this.typeOfPerson);
    }

    private void goEat() throws Exception {
        this.mealsInADay++;
        Room kitchen = this.house.getRoom(myRoom.getNumberOfFloor(), "Kitchen");
        this.goToRoom(kitchen);
        int app = generateNumber(2);
        if (app == 1) {
            myRoom.useOven(this.typeOfPerson);
        } else {
            myRoom.useMicrowave(this.typeOfPerson);
        }
        //FridgeAPI fridgeAPI = (FridgeAPI)kitchen.getApplianceAPIs().get(5);
        FridgeAPI fridgeAPI = (FridgeAPI) kitchen.getApplianceAPIs().stream().filter(applianceAPI -> applianceAPI.getClass().getSimpleName().equals("FridgeAPI")).findAny().orElse(null);
        fridgeAPI.takeFoodOut(generateNumber(2));
        fillDishWasher(generateNumber(4) + 2);
    }

    private boolean noActionIfs(int daytime) throws Exception {
        if (solvingEvents) {
            --timeToFinish;
            if (timeToFinish == 0) {
                solveAnotherEvent(daytime);
            }
            return true;
        }
        else if (isGone) {
            --timeToFinish;
            if (daytime == 72) {
                this.mealsInADay++;
            }
            if (timeToFinish == 0) {
                isGone = false;
                myRoom = myBedroom;
                this.house.enterHouse(this);
            }
            return true;
        }
        else if (isAsleep) {
            if (daytime == wakeUpTime) {
                this.wakeUp();
            }
            return true;
        }
        else if (isBusy) {
            --timeToFinish;
            if (timeToFinish == 0) {
                isBusy = false;
            }
            return true;
        }
        return false;
    }

    private int generateNumber(int num) {
        Random rand = new Random();
        return rand.nextInt(num) + 1;
    }

    private void fillWashingMachine(int numOfClothes) throws Exception{
        this.house.getRoom(myRoom.getNumberOfFloor(), "Bathroom").fillWashingMachine(numOfClothes);
    }

    private void fillDishWasher(int numOfDishes) throws Exception {
        this.house.getRoom(myRoom.getNumberOfFloor(), "Kitchen").fillDishwasher(numOfDishes);
    }

}