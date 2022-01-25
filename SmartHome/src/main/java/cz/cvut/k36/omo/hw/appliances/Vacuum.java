package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Floor;
import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing vacuum.
 */
public class Vacuum extends Appliance {
    private Floor floor;

    /**
     * Constructor.
     * @param room - void
     * @param floor - in which vacuum is
     */
    public Vacuum(Room room, Floor floor) {
        super(2, room);
        this.floor = floor;
        this.setName("Vacuum");
        this.setManual(new Manual(Manual.Difficulty.NOT_SO_EASY_BUT_CAN_BE_DONE, this, 2018));
    }

    /**
     * Method to get floor of vacuum.
     * @return floor
     */
    public Floor getFloor() {
        return floor;
    }

}
