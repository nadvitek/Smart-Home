
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing microwave.
 */
public class Microwave extends Appliance {

    /**
     * Constructor.
     * @param room - in which microwave is
     */
    public Microwave(Room room) {
        super(5, room);
        this.setName("Microwave");
        this.setManual(new Manual(Manual.Difficulty.NOT_SO_EASY_BUT_CAN_BE_DONE, this, 2017));
    }
}
