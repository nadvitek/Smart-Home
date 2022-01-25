
package cz.cvut.k36.omo.hw.appliances;

import cz.cvut.k36.omo.hw.livings.Person;
import cz.cvut.k36.omo.hw.smarthome.Room;
import java.util.List;

/**
 * Class for representing locks.
 */
public class Locks extends Appliance {
    private List<Person> people;

    /**
     * Constructor.
     * @param people - list of people in house
     * @param room - void
     */
    public Locks(List<Person> people, Room room) {
        super(1, room);
        this.people = people;
        this.setName("Locks");
        this.setManual(new Manual(Manual.Difficulty.EASY_FIX, this, 2018));
    }

    /**
     * Method to get list of people in house.
     * @return list of people in house
     */
    public List<Person> getPeople() {
        return people;
    }
}
