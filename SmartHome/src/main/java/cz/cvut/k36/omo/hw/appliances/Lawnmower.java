
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing lawnmower.
 */
public class Lawnmower extends Appliance {
    private int gardenArea;
    private int lastMowedTime;

    /**
     * Constructor.
     * @param room - void
     * @param gardenArea - area of the garden
     */
    public Lawnmower(Room room, int gardenArea) {
        super(3, room);
        this.gardenArea = gardenArea;
        this.lastMowedTime = 0;
        this.setName("Lawnmower");
        this.setManual(new Manual(Manual.Difficulty.NOT_SO_EASY_BUT_CAN_BE_DONE, this, 2016));
    }

    /**
     * Method to get time when garden was last mowed.
     * @return time of last mowing
     */
    public int getLastMowedTime() {
        return lastMowedTime;
    }

    /**
     * Method to set the last time of mowing.
     * @param lastMowedTime - last time of mowing
     */
    public void setLastMowedTime(int lastMowedTime) {
        this.lastMowedTime = lastMowedTime;
    }

    /**
     * Method to get area of garden.
     * @return area of garden
     */
    public int getGardenArea() {
        return gardenArea;
    }
}
