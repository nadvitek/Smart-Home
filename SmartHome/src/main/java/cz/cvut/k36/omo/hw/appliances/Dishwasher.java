
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing dishwasher.
 */
public class Dishwasher extends Appliance {
    private int fullOfDishes; //1-10

    /**
     * Constructor.
     * @param room - room in which dishwasher is
     */
    public Dishwasher(Room room) {
        super(4, room);
        this.fullOfDishes = 0;
        this.setName("Dishwasher");
        this.setManual(new Manual(Manual.Difficulty.CALL_A_HANDYMAN, this, 2020));
    }

    /**
     * Method to get how full dishwasher is.
     * @return integer between 1-10
     */
    public int getFullOfDishes() {
        return fullOfDishes;
    }

    /**
     * Method to set how full dishwasher is.
     * @param fullOfDishes - integer between 1-10
     */
    public void setFullOfDishes(int fullOfDishes) {
        if(fullOfDishes > 10) {
            this.fullOfDishes = 10;
        } else {
            this.fullOfDishes = fullOfDishes;
        }
    }
}
