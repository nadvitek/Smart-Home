
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing toilette.
 */
public class Toilette extends Appliance {

    /**
     * Constructor.
     * @param room - in which toilette is
     */
    public Toilette(Room room) {
        super(2, room);
        this.setName("Toilette");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2015));
    }
}
