package cz.cvut.k36.omo.hw.livings;

import cz.cvut.k36.omo.hw.smarthome.HouseAPI;
import cz.cvut.k36.omo.hw.smarthome.Room;
import java.util.Random;

/**
 * Class for pets.
 */
public abstract class Pet {
    private Room myRoom;
    private boolean canMove;
    private HouseAPI house;

    /**
     * Constructor.
     * @param canMove - tells if it's a moving pet
     * @param api - the house
     */
    public Pet(boolean canMove, HouseAPI api) {
        this.canMove = canMove;
        this.house = api;
    }

    /**
     * Pets move if they can.
     */
    public void useTime() {
        if (canMove) {
            Random rand = new Random();
            if (rand.nextInt(20) <= 3) {
                goToRoom(this.house.getRandomRoom());
            }
        }
    }

    /**
     * Pets go to the room they are sent to.
     * @param room they are sent to
     */
    public void goToRoom(Room room) {
        if (this.myRoom != null) {
            this.myRoom.petLeavesRoom(this);
        }
        this.myRoom = room;
        this.myRoom.petInRoom(this);
    }
}
