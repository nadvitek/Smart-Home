package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class to represent blinds.
 */
public class Blinds extends Appliance {

    /**
     * Constructor.
     * @param room - room in which blinds are
     */
    public Blinds(Room room) {
        super(2, room);
        this.setName("Blinds");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2021));
    }
}
