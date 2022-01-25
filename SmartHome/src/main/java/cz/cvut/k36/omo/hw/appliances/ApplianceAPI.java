package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.House;
import cz.cvut.k36.omo.hw.smarthome.Times;
import cz.cvut.k36.omo.hw.events.EventCreator;

import java.util.Random;

/**
 * Class for representing Appliance API.
 */
public abstract class ApplianceAPI {
    private Appliance appliance;
    private House house;
    private int currentTime;

    /**
     * Constructor.
     * @param appliance - appliance of this API
     * @param house - house in which appliance is
     */
    public ApplianceAPI(Appliance appliance, House house) {
        this.appliance = appliance;
        this.house = house;
    }

    /**
     * Method to turn appliance on.
     * @return true if appliance was set on
     */
    public boolean turnOn() {
        if(this.appliance.isBroken()){
            return false;
        } else {
            this.appliance.setOn(true);
            return true;
        }
    }

    /**
     * Method to set appliance off.
     */
    public void turnOff() {
        this.appliance.setOn(false);
    }

    /**
     * Method to get appliance of this API.
     * @return appliance
     */
    public Appliance getAppliance() {
        return appliance;
    }

    /**
     * Method to get house in which appliance is.
     * @return house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Method to set what time it is.
     * @param currentTime - current time
     */
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Method to know what time it is.
     * @return current time (int)
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Method to use time = get older, set power saving mode according to time, possibly get broken.
     * @param time - current time
     */
    public void useTime(int time) {
        setCurrentTime(time);
        if (appliance.getIsWorking() > 0) {
            appliance.setIsWorking(appliance.getIsWorking() - 1);
        }
        if (time % Times.DAY == Times.SUNRISE) {
            appliance.setPowerSavingMode(false);
        } else if (time % Times.DAY == Times.NIGHT_TIME){
            appliance.setPowerSavingMode(true);
        }
        if (time % Times.DAY == 0) {
            appliance.setDaysOld(appliance.getDaysOld() + 1);
        }
        Random rand = new Random();
        if (rand.nextInt(8000) <= appliance.getDaysOld()) {
            appliance.setBroken(true);
        }
        if(appliance.isBroken() && !appliance.isFixEventCreated()){
            Manual man = getAppliance().getManual();
            switch (man.getDifficulty()) {
                case EASY_FIX:
                    this.house.getEvents().add(EventCreator.getInstance().createEvent(2, 3, this, 1));
                    break;
                case NOT_SO_EASY_BUT_CAN_BE_DONE:
                    this.house.getEvents().add(EventCreator.getInstance().createEvent(4, 3, this, 1));
                    break;
                case CALL_A_HANDYMAN:
                    this.house.getEvents().add(EventCreator.getInstance().createEvent(1, 3, this, 1));
                    break;
            }
            appliance.setFixEventCreated(true);
        }
    }

    /**
     * Method to solve an event.
     * @param extra - number to differentiate between events
     * @param name - who did event
     * @return empty string
     */
    public String solveEvent(int extra, String name) {
        return "";
    }

    /**
     * Method to add to usage of appliance.
     * @param name - who used appliance
     */
    public void addUsage(String name){
        if (appliance.getUsage().containsKey(name)){
            appliance.getUsage().put(name, getAppliance().getUsage().get(name) + 1);
        } else {
            appliance.getUsage().put(name, 1);
        }
    }

    /**
     * Method to calculate consumption of appliance.
     * @return empty string
     */
    public String calculateConsumption(){
        String consumptionMessage = "";
        return consumptionMessage;
    }

    /**
     * Method to calculate usage of appliance.
     * @return empty string
     */
    public String calculateUsage() {
        String usageMessage = "";
        return usageMessage;
    }
}
