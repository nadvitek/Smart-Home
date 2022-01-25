
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing hub.
 */
public class Hub extends Appliance {

    /**
     * Constructor.
     * @param room - in which hub is
     */
    public Hub(Room room) {
        super(2, room);
        this.setName("Hub");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2019));
    }
}
