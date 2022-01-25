package cz.cvut.k36.omo.hw.items;

import cz.cvut.k36.omo.hw.livings.Person;

/**
 * Class that represents bicycle in the house.
 */
public class Bicycle extends Item{
    /**
     * Constructor, sets also the time for using the bike.
     * @param person - gives the Person whose bike it is
     */
    public Bicycle(Person person) {
        super(person);
        this.setUsingBorders(24, 42);
    }
}
