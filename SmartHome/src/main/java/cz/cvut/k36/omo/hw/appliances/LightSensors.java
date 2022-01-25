package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing light sensors.
 */
public class LightSensors extends Appliance {
    private boolean movement;

    /**
     * Constructor.
     * @param room - in which light sensors are
     */
    public LightSensors(Room room) {
        super(2, room);
        this.movement = false;
        this.setOn(true);
        this.setName("Lights sensors");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2021));
    }

    /**
     * Method to set that sensors caught movement.
     * @param movement - true if movement was caught
     */
    public void setMovement(boolean movement) {
        this.movement = movement;
    }

}
