package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

import java.util.HashMap;

/**
 * Class representing appliances.
 */
public abstract class Appliance {
    private Manual manual;
    private boolean isBroken;
    private boolean fixEventCreated;
    private boolean isOn;
    private int consumption;
    private boolean powerSavingMode;
    private int daysOld;
    private Room room;
    private int isWorking;
    private String name;
    private HashMap<String, Integer> usage = new HashMap<>();

    /**
     * Constructor.
     * @param manual - appliance's manual
     * @param room - room in which appliance is
     */
    public Appliance(int manual, Room room) {
        this.isBroken = false;
        this.fixEventCreated = false;
        this.isOn = false;
        this.consumption = 0;
        this.powerSavingMode = true;
        this.daysOld = 0;
        this.room = room;
        this.isWorking = 0;
    }

    /**
     * Method to find out whether appliance is broken.
     * @return true if appliance is broken
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Method to find out whether appliance is on.
     * @return true if appliance is on
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Method to set whether appliance is broken.
     * @param broken - true if appliance is broken
     */
    public void setBroken(boolean broken) {
        if(!broken) {
            setFixEventCreated(false);
        }
        isBroken = broken;
    }

    /**
     * Method to get appliance's manual.
     * @return appliance's manual
     */
    public Manual getManual() {
        return manual;
    }
    protected void setManual(Manual man) {
        this.manual = man;
    }

    /**
     * Method to set whether appliance is on.
     * @param on - true if applliance is on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    /**
     * Method to set appliance to a power saving mode.
     * @param mode true if appliance is to switch to a power saving mode
     */
    public void setPowerSavingMode(boolean mode) {powerSavingMode = mode;}

    /**
     * Method to get the room in which appliance is.
     * @return room in which appliance is
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Method to find out if appliance is working (and for how much time it will be working).
     * @return isWorking - int that says how much longer appliance will be working
     */
    public int getIsWorking() {
        return isWorking;
    }

    /**
     * Method to set if appliance is working and if so, for how long.
     * @param isWorking - how long appliance is to work
     */
    public void setIsWorking(int isWorking) {
        this.isWorking = isWorking;
    }

    /**
     * Method to check if an event to fix appliance was already created.
     * @return fixEventCreated - true if event was already created
     */
    public boolean isFixEventCreated() {
        return fixEventCreated;
    }

    /**
     * Method to set whether an event to fix appliance has been created.
     * @param fixEventCreated - true if event was already created
     */
    public void setFixEventCreated(boolean fixEventCreated) {
        this.fixEventCreated = fixEventCreated;
    }

    /**
     * Method to add to how many times appliance was used.
     */
    public void addToConsumption() {
        if (powerSavingMode) {
            this.consumption++;
        } else {
            this.consumption += 2;
        }
    }

    /**
     * Method to get consumption (how many times appliance was used).
     * @return consumption - how many times appliance was used
     */
    public int getConsumption() {
        return consumption;
    }

    /**
     * Method to get appliance's name.
     * @return name of appliance
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set appliance's name.
     * @param name - appliance's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get usage.
     * @return usage - hash map of who used appliance and how many time
     */
    public HashMap<String, Integer> getUsage() {
        return usage;
    }

    /**
     * Method to get how old appliance is.
     * @return daysOld
     */
    public int getDaysOld() {
        return daysOld;
    }

    /**
     * Method to set how old appliance is.
     * @param daysOld
     */
    public void setDaysOld(int daysOld) {
        this.daysOld = daysOld;
    }
}


