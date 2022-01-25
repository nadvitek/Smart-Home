
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing television.
 */
public class Television extends Appliance {

    /**
     * Constructor.
     * @param room - in which television is
     */
    public Television(Room room) {
        super(4, room);
        this.setName("Television");
        this.setManual(new Manual(Manual.Difficulty.NOT_SO_EASY_BUT_CAN_BE_DONE, this, 2020));
    }
}
