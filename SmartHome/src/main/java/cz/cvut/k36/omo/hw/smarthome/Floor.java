package cz.cvut.k36.omo.hw.smarthome;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of a floor in the house.
 */
public class Floor {
    private final List<Room> rooms = new ArrayList();
    private final int numberOfFloor;

    /**
     * Constructor - fills itself with rooms.
     * @param numberOfFloor - the number of this floor
     * @param rooms - array of rooms
     * @throws Exception
     */
    public Floor(int numberOfFloor, int rooms[]) throws Exception {
        this.numberOfFloor = numberOfFloor;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    for (int j = 0; j < rooms[i]; j++) {
                        this.rooms.add(new Room("Living room", numberOfFloor));
                    }   break;
                case 1:
                    for (int j = 0; j < rooms[i]; j++) {
                        this.rooms.add(new Room("Kitchen", numberOfFloor));
                    }   break;
                case 2:
                    for (int j = 0; j < rooms[i]; j++) {
                        this.rooms.add(new Room("Bedroom", numberOfFloor));
                    }   break;
                case 3:
                    for (int j = 0; j < rooms[i]; j++) {
                        this.rooms.add(new Room("Bathroom", numberOfFloor));
                    }   break;
                default:
                    throw new Exception("Something went wrong!\n");
            }
        }
    }

    /**
     * Method that returns the list of rooms.
     * @return the list of rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Method that returns the number of this floor.
     * @return the number of this floor
     */
    public int getNumberOfFloor(){
        return this.numberOfFloor;
    }
}
