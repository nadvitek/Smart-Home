package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.smarthome.Room;

/**
 * Class for representing dust sensors.
 */
public class DustSensors extends Appliance {
    private int dustInRoom;

    /**
     * Constructor.
     * @param room in which dust sensors are
     */
    public DustSensors(Room room) {
        super(2, room);
        this.setOn(true);
        this.setName("Dust sensors");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2021));
    }

    /**
     * Method to get how much dust is in room.
     * @return integer between 1-20
     */
    public int getDustInRoom() {
        return dustInRoom;
    }

    /**
     * Method to set how much dust there is in the room.
     * @param dustInRoom - integer between 1-20
     */
    public void setDustInRoom(int dustInRoom) {
        this.dustInRoom = dustInRoom;
    }
}
