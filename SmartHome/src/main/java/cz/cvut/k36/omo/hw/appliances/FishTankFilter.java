
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing fish tank filter.
 */
public class FishTankFilter extends Appliance {
    private boolean clean;
    private int lastCleaned;

    /**
     * Constructor.
     * @param room - in which filter is
     */
    public FishTankFilter(Room room) {
        super(2, room);
        this.setName("Fish tank filter");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2019));
    }

    /**
     * Method to find out if fish tank is clean.
     * @return true if it's clean
     */
    public boolean isClean() {
        return clean;
    }

    /**
     * Method to clean fish tank.
     * @param time - time of cleaning
     */
    public void cleanTank(int time) {
        this.clean = true;
        this.lastCleaned = time;
    }

    /**
     * Method to set that fish tank is filthy.
     */
    public void tankFilthy() {
        this.clean = false;
    }

    /**
     * Method to find out when fish tank was last clened.
     * @return time of last cleaning
     */
    public int getLastCleaned() {
        return lastCleaned;
    }
}
