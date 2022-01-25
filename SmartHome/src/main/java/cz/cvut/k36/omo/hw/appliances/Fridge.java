
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing fridge.
 */
public class Fridge extends Appliance {
    private int stacked;

    /**
     * Constructor.
     * @param room - in which fridge is
     */
    public Fridge(Room room) {
        super(6, room);
        this.stacked = 10;
        this.setOn(true);
        this.setName("Fridge");
        this.setManual(new Manual(Manual.Difficulty.CALL_A_HANDYMAN, this, 2018));
    }

    /**
     * Method to find out how much food there is in fridge.
     * @return integer between 1-40
     */
    public int getStacked() {
        return stacked;
    }

    /**
     * Method to set how much food there is in fridge.
     * @param stacked - integer between 1-40
     */
    public void setStacked(int stacked) {
        this.stacked = stacked;
    }

}
