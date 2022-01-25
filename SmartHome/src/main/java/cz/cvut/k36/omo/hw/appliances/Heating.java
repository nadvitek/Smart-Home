
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing heating.
 */
public class Heating extends Appliance {

    /**
     * Constructor.
     * @param room - in which heating is
     */
    public Heating(Room room) {
        super(3, room);
        this.setName("Heating");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2016));
    }
}
