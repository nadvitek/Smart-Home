
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing oven.
 */
public class Oven extends Appliance {

    /**
     * Constructor.
     * @param room - in ehich oven is
     */
    public Oven(Room room) {
        super(3, room);
        this.setName("Oven");
        this.setManual(new Manual(Manual.Difficulty.CALL_A_HANDYMAN, this, 2014));
    }
}
