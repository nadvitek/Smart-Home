
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing washing machine.
 */
public class WashingMachine extends Appliance {
    private int fullOfClothes;

    /**
     * Constructor.
     * @param room - in which washing machine is
     */
    public WashingMachine(Room room) {
        super(6, room);
        this.setName("Washing machine");
        this.setManual(new Manual(Manual.Difficulty.NOT_SO_EASY_BUT_CAN_BE_DONE, this, 2021));
    }

    /**
     * Method for finding out how full washing machine is.
     * @return integer between 1-20
     */
    public int getFullOfClothes() {
        return fullOfClothes;
    }

    /**
     * Method to set how full of clothes washing machine is.
     * @param fullOfClothes - integer between 1-20
     */
    public void setFullOfClothes(int fullOfClothes) {
        this.fullOfClothes = fullOfClothes;
    }
}
