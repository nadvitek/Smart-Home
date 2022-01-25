
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing lights.
 */
public class Lights extends Appliance {
    private LightSensorsAPI sensorsAPI;

    /**
     * Constructor.
     * @param room - in which lights are
     */
    public Lights(Room room) {
        super(1, room);
        this.setName("Lights");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2022));
    }

}
